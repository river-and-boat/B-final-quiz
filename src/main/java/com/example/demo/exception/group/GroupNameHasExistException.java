package com.example.demo.exception.group;

public class GroupNameHasExistException extends RuntimeException {
    public GroupNameHasExistException(String message) {
        super(message);
    }
}
