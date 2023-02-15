package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@NoArgsConstructor
@Getter
@Component
public class MovieMapper implements Function<Movie, MovieDto> {

    @Override
    public MovieDto apply(Movie movie) {  // todo MupStruck Lib use
        return new MovieDto(
                movie.getId(),
                movie.getNameRussian(),
                movie.getNameNative(),
                movie.getDescription(),
                movie.getPicturePath(),
                movie.getYearOfRelease(),
                movie.getRating(),
                movie.getPrice(),
                movie.getVotes());

    }
}
