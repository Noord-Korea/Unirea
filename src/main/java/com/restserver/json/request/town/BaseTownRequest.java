package com.restserver.json.request.town;

import com.models.AccessToken;

public class BaseTownRequest {
    private String token;

    private int townId;

    public BaseTownRequest(String token, int townId) {
        this.townId = townId;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public int getTownId() {
        return townId;
    }
}
