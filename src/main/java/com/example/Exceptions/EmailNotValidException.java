package com.example.Exceptions;

public class EmailNotValidException extends RuntimeException {

    public EmailNotValidException(String message) {
        super(message);
    }
}
