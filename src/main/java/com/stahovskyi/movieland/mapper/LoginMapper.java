package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.service.dto.response.LoginDto;
import com.stahovskyi.movieland.service.entity.response.Login;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper {

    LoginDto toLoginDto(Login login);

}
