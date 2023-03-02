package com.stahovskyi.movieland.exception;

// todo need throw code ??
public class MovieNotFoundException extends RuntimeException {

    private static final String NO_MOVIE_BY_ID_MASSAGE = "Not found movie by provided id: %s";


    public MovieNotFoundException(int movieId) {
        super(String.format(NO_MOVIE_BY_ID_MASSAGE, movieId));
    }

}
