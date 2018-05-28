package com.restserver.json.request.account;

public abstract class BaseRequest {
    private String token;

    BaseRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
