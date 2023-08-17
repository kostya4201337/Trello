package com.example.Trello.services.exception;

public class NoCardFoundException extends RuntimeException{
    public NoCardFoundException(final String message) {
        super(message);
    }
}
