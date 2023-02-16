package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.repository.MovieRepository;
import com.stahovskyi.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final static int RANDOM_MOVIE_NUMBER = 3;

    private final MovieRepository movieRepository;


    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getRandom() {
        List<Movie> movieList = movieRepository.findAll();
        List<Movie> result = new ArrayList<>();

        for (int i = 0; i < RANDOM_MOVIE_NUMBER; i++) {
            result.add(movieList.get(new Random().nextInt(1, movieList.size())));
        }
        return result;
    }

}
