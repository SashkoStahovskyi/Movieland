package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.User;
import com.stahovskyi.movieland.exception.NotFoundException;
import com.stahovskyi.movieland.repository.UserRepository;
import com.stahovskyi.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        log.info("findByEmail fetch user");
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(email));
    }

    @Transactional(readOnly = true)
    @Override
    public String getNickname(String email) {
        log.info("get nick name fetch user");
        return userRepository.findUserByEmail(email)
                .map(User::getNickName)
                .orElseThrow(() -> new NotFoundException(email));
    }

}
