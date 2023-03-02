package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.web.controller.request.MovieRequest;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getAll(MovieRequest request);

    Movie getById(int movieId);

    List<Movie> getAllByGenreId(int genreId);

    List<Movie> getAllRandom();
}
