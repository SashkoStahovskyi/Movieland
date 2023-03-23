package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.User;

public interface UserService {

    User findByEmail(String email);

    String getNickname(String email);

}

