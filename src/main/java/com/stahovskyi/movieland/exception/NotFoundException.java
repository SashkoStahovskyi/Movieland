package com.stahovskyi.movieland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String NOT_FOUND_BY_ID_MASSAGE = "Not found by provided id: %s";
    private static final String NOT_FOUND_BY_EMAIL_MASSAGE = "Not found by provided email: %s";

    public NotFoundException(int id) {
        super(String.format(NOT_FOUND_BY_ID_MASSAGE, id));

    }

    public NotFoundException(String email) {
        super(String.format(NOT_FOUND_BY_EMAIL_MASSAGE, email));

    }

}

