package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.GenreDto;
import com.stahovskyi.movieland.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {


    List<GenreDto> toGenreDtoList(List<Genre> all);

}
