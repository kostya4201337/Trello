package com.example.Trello.exception.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApiError {

    private final String message;

    private final Timestamp timestamp;
}