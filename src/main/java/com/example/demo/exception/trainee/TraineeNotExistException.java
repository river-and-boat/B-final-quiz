package com.example.demo.exception.trainee;

public class TraineeNotExistException extends RuntimeException {
    public TraineeNotExistException(String message) {
        super(message);
    }
}
