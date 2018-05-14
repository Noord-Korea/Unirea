package com.restserver.json.request.account;

public class BaseRequest {
    private String token;

    public BaseRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
