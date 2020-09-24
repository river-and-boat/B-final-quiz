package com.example.demo.exception.trainer;

public class TrainerNotExistException extends RuntimeException {
    public TrainerNotExistException(String message) {
        super(message);
    }
}
