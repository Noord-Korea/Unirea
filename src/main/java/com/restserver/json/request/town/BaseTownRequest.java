package com.restserver.json.request.town;

public class BaseTownRequest {
    private String token;

    public BaseTownRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
