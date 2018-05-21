package com.restserver.json.request.account;

import com.models.AccessToken;

public class Delete extends BaseRequest{
    private String username;

    public Delete(AccessToken token, String username) {
        super(token);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
