package com.example.demo.controller;

import com.example.demo.model.dto.AnnonceDto;
import com.example.demo.model.jpa.AnnonceJpa;
import com.example.demo.service.AnnonceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jixia
 * @Description TODO
 * @date 20/11/2023-16:05
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping("/annonces")
    public ResponseEntity<?> getAllAnnonces(){
        List<AnnonceDto> annonces = annonceService.getAllAnnonces();
        if(annonces != null && !annonces.isEmpty()){
            return new ResponseEntity<List<AnnonceDto>>(annonces,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("user/{id}/annonce")
    public ResponseEntity<?> getAnnonceByUserId(@PathVariable Integer id) {

        AnnonceDto annonce = annonceService.getAnnonceByUser(id);
        if(annonce!=null){
            return new ResponseEntity<>(annonce, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("annonces/ville/{ville}")
    public ResponseEntity<?> getAnnoncesByVille(@PathVariable String ville) {

        List<AnnonceDto> annonces = annonceService.getAnnoncesByVille(ville);
        if(annonces!=null && !annonces.isEmpty()){
            return new ResponseEntity<>(annonces,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("annonces/type/{typeService}")
    public ResponseEntity<List<AnnonceDto>> getAnnoncesByType(@PathVariable String typeService) {

        List<AnnonceDto> annonces = annonceService.getAnnoncesByType(typeService);
        if(annonces!=null && !annonces.isEmpty()){
            return new ResponseEntity<>(annonces, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
