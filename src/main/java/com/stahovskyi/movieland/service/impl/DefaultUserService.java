package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.User;
import com.stahovskyi.movieland.exception.NotFoundException;
import com.stahovskyi.movieland.repository.UserRepository;
import com.stahovskyi.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(email));
    }

    @Override
    public String getNickname(String email) {
        return userRepository.findUserByEmail(email)
                .map(User::getNickName)
                .orElseThrow(() -> new NotFoundException(email));
    }

}
