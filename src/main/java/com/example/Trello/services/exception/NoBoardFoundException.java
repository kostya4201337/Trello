package com.example.Trello.services.exception;

public class NoBoardFoundException extends RuntimeException{
    public NoBoardFoundException(final String message) {
        super(message);
    }
}
