package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.User;
import com.stahovskyi.movieland.exception.NotFoundException;
import com.stahovskyi.movieland.repository.UserRepository;
import com.stahovskyi.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(email));
    }

    @Transactional(readOnly = true)
    @Override
    public String getNickname(String email) {
        return userRepository.findUserByEmail(email)
                .map(User::getNickName)
                .orElseThrow(() -> new NotFoundException(email));
    }

}
