package com.example.demo.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;


/**
 * @author jixia
 * @Description TODO
 * @date 25/12/2023-16:33
 */
@Data
@Entity(name = "annonce")
public class AnnonceJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="type_service")
    String typeService;

    String ville;

    @Column(name="code_postal")
    String codePostal;

    @Column(name="date_debut")
    String dateDebut;

    @Column(name="date_fin")
    String dateFin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", unique = true)
    @JsonIgnore
    private UserJpa user;

}
