package com.restserver.json.response;

public class Reply {
    private int status;
    private String message;

    public Reply(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
