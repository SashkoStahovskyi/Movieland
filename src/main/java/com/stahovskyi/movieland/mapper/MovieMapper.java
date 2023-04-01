package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.dto.request.MovieRequestDto;
import com.stahovskyi.movieland.service.dto.response.DetailedMovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MovieMapper {

    List<MovieDto> toMovieDtoList(List<Movie> movieList);

    @Mapping(target = "yearOfRelease", expression = "java(movie.getYearOfRelease().getYear())")
    MovieDto toMovieDto(Movie movie);

    @Mapping(target = "genres", source = "genre")
    @Mapping(target = "yearOfRelease", expression = "java(movie.getYearOfRelease().getYear())")
    DetailedMovieDto toDetailedMovieDto(Movie movie);

    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "year")
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "vote", ignore = true)
    @Mapping(target = "rate", ignore = true)
    Movie toMovie(MovieRequestDto movieRequestDto);

    @Named("year")
    default LocalDate mapYearToLocalDate(String year) {
        return LocalDate.of(LocalDate.parse(year).getYear(), 1, 1);
    }

}
