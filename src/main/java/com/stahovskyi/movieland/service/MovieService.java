package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getRandom();

}
