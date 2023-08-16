package com.example.Trello.exception;

import com.example.Trello.exception.model.ApiError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    ApiError defaultHandler(final Throwable t) {
        return new ApiError(t.getMessage(), new Timestamp(System.currentTimeMillis()));
    }
}