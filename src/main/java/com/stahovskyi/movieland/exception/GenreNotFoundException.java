package com.stahovskyi.movieland.exception;

public class GenreNotFoundException extends RuntimeException {

    private static final String NO_GENRE_BY_ID_MASSAGE = "Not found genre by provided id: %s";

    public GenreNotFoundException(int genreId) {
        super(String.format(NO_GENRE_BY_ID_MASSAGE, genreId));
    }

}
