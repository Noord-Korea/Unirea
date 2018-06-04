package com.restserver.json.response;

import com.google.gson.Gson;

public class Reply {
    private Status status;
    private String message;

    public Reply(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Reply(Status status, Object obj){
        this.status = status;
        Gson gson = new Gson();
        message = gson.toJson(obj);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
