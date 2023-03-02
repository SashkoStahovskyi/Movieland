package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.DetailedMovieDto;
import com.stahovskyi.movieland.dto.GenreDto;
import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    List<MovieDto> toMovieDtoList(List<Movie> movieList);

    @Mapping(target = "yearOfRelease", expression = "java(movie.getYearOfRelease().getYear())")
    MovieDto toMovieDto(Movie movie);

    List<GenreDto> toGenreDtoList(List<Genre> genreList);

    @Mapping(target = "genres", source = "genre")
    @Mapping(target = "yearOfRelease", expression = "java(movie.getYearOfRelease().getYear())")
    DetailedMovieDto toDetailedMovieDto(Movie movie);


}
