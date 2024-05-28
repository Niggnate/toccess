package com.nursoft.toccess.controllers.exceptions;

public class KeyNotFoundException extends Throwable {
    private final String message;

    public KeyNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "KeyNotFoundException{" +
                "message='" + message + '\'' +
                '}';
    }
}
