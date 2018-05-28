package com.restserver.json.request.town;

import com.models.AccessToken;

public class BaseTownRequest {
    private String token;

    public BaseTownRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
