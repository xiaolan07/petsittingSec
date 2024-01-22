package com.example.demo.dao;

import com.example.demo.model.jpa.AnnonceJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jixia
 * @Description TODO
 * @date 20/11/2023-15:46
 */
public interface AnnonceDao extends JpaRepository<AnnonceJpa, Integer> {
    AnnonceJpa findAnnonceJpaByUser_Id(Integer userId);

    List<AnnonceJpa> findAnnonceJpasByVille (String ville);

    List<AnnonceJpa> findAnnonceJpasByTypeService (String typeService);


}
