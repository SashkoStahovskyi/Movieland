package com.stahovskyi.movieland.web.controller;


import com.stahovskyi.movieland.service.dto.response.LoginDto;
import com.stahovskyi.movieland.service.dto.request.LoginRequestDto;
import com.stahovskyi.movieland.mapper.LoginMapper;
import com.stahovskyi.movieland.service.impl.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final LoginMapper loginMapper;


    @PostMapping("/login")
    protected LoginDto getLogin(@RequestBody LoginRequestDto request) {
        log.info("Signing up for user {} ", request.getEmail());
        return loginMapper.toLoginDto(loginService.getLogin(request.getEmail(), request.getPassword()));
    }

}
