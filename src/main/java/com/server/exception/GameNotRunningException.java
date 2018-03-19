package com.server.exception;

public class GameNotRunningException extends Exception {
    public GameNotRunningException() { super(); }
    public GameNotRunningException(String message) { super(message); }
    public GameNotRunningException(String message, Throwable cause) { super(message, cause); }
    public GameNotRunningException(Throwable cause) { super(cause); }
}