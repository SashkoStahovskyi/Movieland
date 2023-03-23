package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.service.SecurityService;
import com.stahovskyi.movieland.service.UserService;
import com.stahovskyi.movieland.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtSecurityService implements SecurityService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Override
    public String generateToken(String email) {
        final UserDetails userDetails = loadUserByEmail(email);
        return jwtUtil.generateToken(userDetails);
    }


    @Override
    public UserDetails loadUserByEmail(String email) {
        com.stahovskyi.movieland.entity.User user = userService.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String userRoleName = user.getRole().getName();

        return new User(email, encodedPassword,
                Collections.singleton(new SimpleGrantedAuthority(userRoleName)));
    }

}
