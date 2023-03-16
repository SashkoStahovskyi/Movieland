package com.stahovskyi.movieland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String NOT_FOUND_MASSAGE = "Not found by provided id: %s";

    public NotFoundException(int id) {
        super(String.format(NOT_FOUND_MASSAGE, id));
    }

}

