package com.stahovskyi.movieland.service.util;

import com.stahovskyi.movieland.entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    private final static int RANDOM_MOVIE_COUNT = 3;

    public static List<Movie> chooseRandom(List<Movie> movieList) {
        List<Movie> result = new ArrayList<>();

        for (int i = 0; i < RANDOM_MOVIE_COUNT; i++) {
            result.add(movieList.get(new Random().nextInt(1, movieList.size())));
        }

        return result;
    }


}
