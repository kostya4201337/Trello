package com.example.Trello.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoCardFoundException extends RuntimeException{
    public NoCardFoundException(final String message) {
        super(message);
    }
}
