package com.example.demo.model.dto;

import com.example.demo.service.UserService;
import com.example.demo.service.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jixia
 * @Description TODO
 * @date 25/12/2023-16:48
 */
public class AnnonceDto {

    Integer id;

    String typeService;
    String ville;
    String codePostal;
    String dateDebut;
    String dateFin;

    UserDto user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    /**
     * @author jixia
     * @Description TODO
     * @date 2023-09-03-15:50
     */
    @RestController
    @RequestMapping("/user")
    public static class UserController {
        private final UserService userService;
        private final UserMapper userMapper;


        @Autowired
        public UserController(UserService userService, UserMapper userMapper) {
            this.userService = userService;
            this.userMapper = userMapper;
        }

        @GetMapping("users")
        public ResponseEntity<List<UserDto>> getAllUsers() {
            List<UserDto> userList = userService.getAllUsers();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }

        @GetMapping("user/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
            UserDto user = userService.getUserById(id);

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("user")
        public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
            UserDto createdUser = userService.createUser(userDto);
            if (createdUser != null) {
                return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("L'email n'est pas unique.");
            }
        }

        @PostMapping("user/{id}/annonce")
        public  ResponseEntity<?> createAnnonce(@PathVariable Integer id,@RequestBody AnnonceDto annonceDto){
            Integer userId = id;
            AnnonceDto createAnnonceDto = userService.createAnnonceForUser(userId,annonceDto);
            if(createAnnonceDto!=null){
                return new ResponseEntity<>(createAnnonceDto,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }


        @PutMapping("user/{id}")
        public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
            UserDto updatedUser = userService.updateUser(id, user);
            if (updatedUser != null) {
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("deleteUser/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
            try {
                userService.deleteUserById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            } catch (EntityNotFoundException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        }


    }
}
