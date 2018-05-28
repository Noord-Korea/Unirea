package com.restserver.json.request.town;

import com.models.AccessToken;

public class BaseTownRequest {
    private AccessToken token;

    public BaseTownRequest(AccessToken token) {
        this.token = token;
    }

    public AccessToken getToken() {
        return token;
    }
}
