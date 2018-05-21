package com.restserver.json.request.account;

import com.models.AccessToken;

public class BaseRequest {
    private AccessToken token;

    public BaseRequest(AccessToken token) {
        this.token = token;
    }

    public AccessToken getToken() {
        return token;
    }
}
