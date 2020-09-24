package com.example.demo.exception.group;

public class GroupNotExistException extends RuntimeException {
    public GroupNotExistException(String message) {
        super(message);
    }
}
