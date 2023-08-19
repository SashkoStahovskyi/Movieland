package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.dto.request.MovieRequestDto;
import com.stahovskyi.movieland.service.dto.response.DetailedMovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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
    Movie toMovie(MovieRequestDto movieRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "yearOfRelease", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    void update(@MappingTarget Movie movie, MovieRequestDto movieRequestDto);

    @Named("year")
    default LocalDate mapYearToLocalDate(int year) {
        return LocalDate.of(year, 1, 1);
    }

}
