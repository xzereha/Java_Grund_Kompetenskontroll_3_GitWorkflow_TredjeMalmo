package com.example.Exceptions;

public class RegNotValidException extends RuntimeException {
    public RegNotValidException(String message) {
        super(message);
    }
}
