package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import com.stahovskyi.movieland.service.entity.request.MovieRequest;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getAll(MovieRequest request);

    Movie getById(int movieId, CurrencyType currencyType);

    List<Movie> getAllByGenreId(int genreId);

    List<Movie> getRandom();

}
