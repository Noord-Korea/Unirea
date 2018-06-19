package com.restserver.exception;

public class PlayerHasTownException extends Exception {
    public PlayerHasTownException() {
    }

    public PlayerHasTownException(String message) {
        super(message);
    }
}
