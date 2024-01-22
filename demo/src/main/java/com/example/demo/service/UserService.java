package com.example.demo.service;
import com.example.demo.dao.UserDao;
import com.example.demo.model.dto.AnnonceDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.jpa.AnnonceJpa;
import com.example.demo.model.jpa.UserJpa;
import com.example.demo.service.mapper.AnnonceMapper;
import com.example.demo.service.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    @Autowired
    public UserMapper userMapper;

    @Autowired
    public AnnonceMapper annonceMapper;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserDto> getAllUsers() {
        List<UserJpa> usersJpa = userDao.findAll();
        List<UserDto> usersDto = new ArrayList<UserDto>();
        for(int i=0;i<usersJpa.size();i++){
            UserDto userDto = userMapper.toDto(usersJpa.get(i));
            usersDto.add(userDto);
        }
        return usersDto;
    }

    public UserDto getUserById(Integer userId) {
        UserJpa userJpa = userDao.findById(userId).orElse(null);
        UserDto userDto = userMapper.toDto(userJpa);
        return userDto;
    }

    public boolean isEmailExiste(String email) {
        Optional<UserJpa> user = userDao.findByEmail(email);
        if(user != null && !user.isEmpty()){
            return true;
        }
        return false;
    }

    public UserDto createUser(UserDto userDto) {
        UserJpa userJpa = userMapper.toJpa(userDto);
        if (!isEmailExiste(userDto.getEmail())) {
            UserJpa createUserJpa = userDao.save(userJpa);
            return userMapper.toDto(createUserJpa);
        } else {
            return null;
        }
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        UserJpa userJpa = userMapper.toJpa(userDto);
        UserJpa updateUserJpa = userDao.save(userJpa);
        return userMapper.toDto(updateUserJpa);
    }

    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }

    public AnnonceDto createAnnonceForUser(Integer userId, AnnonceDto annonceDto){
        UserJpa userJpa = userDao.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        AnnonceJpa annonceJpa = annonceMapper.toJpa(annonceDto);

        annonceJpa.setUser(userJpa);
        userJpa.setAnnonce(annonceJpa);
        userDao.save(userJpa);
        AnnonceDto createAnnonceDto = annonceMapper.toDto(annonceJpa);
        return createAnnonceDto;
    }

}
