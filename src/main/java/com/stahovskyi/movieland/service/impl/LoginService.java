package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.exception.NotFoundException;
import com.stahovskyi.movieland.service.SecurityService;
import com.stahovskyi.movieland.service.UserService;
import com.stahovskyi.movieland.service.entity.response.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final SecurityService securityService;
    private final UserService userService;


    public Login getLogin(String email, String password) {
        String token = getAuthentication(email, password).getBody();
        String nickname = userService.getNickname(email);
        return new Login(nickname, token);
    }

    private ResponseEntity<String> getAuthentication(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = securityService.generateToken(email);

        return !token.isEmpty() ? ResponseEntity.ok(token) : ResponseEntity.status(400)
                .body(new NotFoundException(email).getMessage());
    }

}
