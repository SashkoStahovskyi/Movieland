package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.GenreDto;
import com.stahovskyi.movieland.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toGenreDto(Genre genre);

}
