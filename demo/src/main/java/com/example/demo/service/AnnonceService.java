package com.example.demo.service;
import com.example.demo.dao.AnnonceDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.dto.AnnonceDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.jpa.AnnonceJpa;
import com.example.demo.model.jpa.UserJpa;
import com.example.demo.service.mapper.AnnonceMapper;
import com.example.demo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * @author jixia
 * @Description TODO
 * @date 20/11/2023-16:06
 */
@Service
public class AnnonceService {

    private final AnnonceDao annonceDao;

    @Autowired
    public AnnonceMapper annonceMapper;
    private final UserDao userDao;

    @Autowired
    public AnnonceService(AnnonceDao annonceDao,
                          UserDao userDao) {
        this.annonceDao = annonceDao;
        this.userDao = userDao;
    }

    public List<AnnonceDto> getAllAnnonces() {
        List<AnnonceJpa> annoncesJpa = annonceDao.findAll();
        List<AnnonceDto> annoncesDto = annonceMapper.toListDto(annoncesJpa);
        return annoncesDto;
    }


    public AnnonceDto getAnnonceByUser(Integer userId) {
        AnnonceJpa annonceJpa = annonceDao.findAnnonceJpaByUser_Id(userId);
        AnnonceDto annonceDto = annonceMapper.toDto(annonceJpa);
        return annonceDto;
    }

    public List<AnnonceDto> getAnnoncesByVille(String ville){
        List<AnnonceJpa> annoncesJpa =  annonceDao.findAnnonceJpasByVille(ville);
        List<AnnonceDto> annoncesDto = annonceMapper.toListDto(annoncesJpa);
        return annoncesDto;
    }

    public List<AnnonceDto> getAnnoncesByType(String typeService){
        List<AnnonceJpa> annoncesJpa =  annonceDao.findAnnonceJpasByTypeService(typeService);
        List<AnnonceDto> annoncesDto = annonceMapper.toListDto(annoncesJpa);
        return annoncesDto;
    }


}
