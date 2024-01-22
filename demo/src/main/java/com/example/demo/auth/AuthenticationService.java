package com.example.demo.auth;

import com.example.demo.config.JwtService;
import com.example.demo.dao.UserDao;
import com.example.demo.exception.EmailExistsException;
import com.example.demo.model.jpa.Role;
import com.example.demo.model.jpa.UserJpa;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author jixia
 * @Description TODO
 * @date 22/01/2024-20:22
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    public <option>AuthenticationResponse register(RegisterRequest request) throws EmailExistsException {
        var user = UserJpa.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        if (userService.isEmailExiste(request.getEmail())) {
            // L'email existe déjà, vous pouvez choisir de lancer une exception ici
            throw new EmailExistsException("L'email existe déjà");
        } else {
            userDao.save(user);
            var jwtToken = jwtService.gengrateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userDao.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.gengrateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
