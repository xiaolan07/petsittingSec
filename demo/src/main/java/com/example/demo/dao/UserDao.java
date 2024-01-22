package com.example.demo.dao;
import com.example.demo.model.jpa.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author jixia
 * @Description TODO
 * @date 2023-09-03-16:40
 */


public interface UserDao extends JpaRepository<UserJpa, Integer> {
    Optional<UserJpa> findByEmail(String email);
}

