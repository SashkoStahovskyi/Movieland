package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.dto.request.MovieRequestDto;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import com.stahovskyi.movieland.service.entity.request.MovieRequest;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(MovieRequest request);

    Movie getById(int movieId, CurrencyType currencyType);

    List<Movie> getAllByGenreId(int genreId);

    List<Movie> getRandom();

    Movie add(MovieRequestDto movieRequestDto);

    Movie update(int id, MovieRequestDto movieRequestDto);
}
