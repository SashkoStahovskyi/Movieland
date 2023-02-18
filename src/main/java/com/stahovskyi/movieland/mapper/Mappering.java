package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.GenreDto;
import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Mappering {

    List<MovieDto> toMovieDtoList(List<Movie> movieList);

    List<GenreDto> toGenreDtoList(List<Genre> genreList);
}
