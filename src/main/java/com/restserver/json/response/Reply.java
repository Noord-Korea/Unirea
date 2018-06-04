package com.restserver.json.response;

public class Reply {
    private Status status;
    private String message;

    public Reply(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
