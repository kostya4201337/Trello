package com.example.Trello.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoBoardFoundException extends RuntimeException{
    public NoBoardFoundException(final String message) {
        super(message);
    }
}
