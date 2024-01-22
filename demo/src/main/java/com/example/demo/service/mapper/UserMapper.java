package com.example.demo.service.mapper;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jixia
 * @Description TODO
 * @date 25/12/2023-15:18
 */
@Service
public class UserMapper {
    @Autowired
    private static AnnonceMapper annonceMapper;

    @Autowired
    private static UserMapper userMapper;

    public UserJpa toJpa(UserDto userDto) {
        if (userDto == null){
            return null;
        }

        UserJpa userJpa = new UserJpa();
        userJpa.setId(userDto.getId());
        userJpa.setUsername(userDto.getUsername());
        userJpa.setEmail(userDto.getEmail());
        userJpa.setIsPublier(userDto.getPublier());
        userJpa.setPassword(userDto.getPassword());

        return userJpa;
    }
    public UserDto toDto(UserJpa userJpa){

        if (userJpa == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userJpa.getId());
        userDto.setEmail(userJpa.getEmail());
        userDto.setUsername(userJpa.getUsername());
        userDto.setPublier(userJpa.getIsPublier());
        userDto.setPassword(userJpa.getPassword());
        return userDto;
    }
}
