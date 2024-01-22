package com.example.demo.service.mapper;

import com.example.demo.dao.UserDao;
import com.example.demo.model.dto.AnnonceDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.jpa.AnnonceJpa;
import com.example.demo.model.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jixia
 * @Description TODO
 * @date 25/12/2023-17:10
 */
@Service
public class AnnonceMapper {

    private static UserMapper userMapper = new UserMapper();
    @Autowired
    private static AnnonceMapper annonceMapper;

    @Autowired
    public AnnonceMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public static AnnonceJpa toJpa(AnnonceDto annonceDto) {
        if(annonceDto == null){
            return null;
        }
        AnnonceJpa annonceJpa = new AnnonceJpa();
        UserDto userDto = annonceDto.getUser();
        UserJpa userJpa = userMapper.toJpa(userDto);
        annonceJpa.setId(annonceDto.getId());
        annonceJpa.setCodePostal(annonceDto.getCodePostal());
        annonceJpa.setDateDebut(annonceDto.getDateDebut());
        annonceJpa.setDateFin(annonceDto.getDateFin());
        annonceJpa.setTypeService(annonceDto.getTypeService());
        annonceJpa.setVille(annonceDto.getVille());
        annonceJpa.setUser(userJpa);
        return annonceJpa;
    }
    public static AnnonceDto toDto(AnnonceJpa annonceJpa){
        if(annonceJpa == null){
            return null;
        }
        AnnonceDto annonceDto = new AnnonceDto();
        UserJpa userJpa = annonceJpa.getUser();
        UserDto userDto = userMapper.toDto(userJpa);
        annonceDto.setId(annonceJpa.getId());
        annonceDto.setCodePostal(annonceJpa.getCodePostal());
        annonceDto.setDateDebut(annonceJpa.getDateDebut());
        annonceDto.setDateFin(annonceJpa.getDateFin());
        annonceDto.setTypeService(annonceJpa.getTypeService());
        annonceDto.setVille(annonceJpa.getVille());
        annonceDto.setUser(userDto);
        return annonceDto;
    }

    public static List<AnnonceDto> toListDto(List<AnnonceJpa> annoncesJpa){
        List<AnnonceDto> annoncesDto = new ArrayList<>();
        for(int i=0;i<annoncesJpa.size();i++){
            AnnonceDto annonceDto = annonceMapper.toDto(annoncesJpa.get(i));
            annoncesDto.add(annonceDto);
        }
        return annoncesDto;
    }
}
