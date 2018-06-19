package com.restserver.json.request.account;

public class Delete extends BaseRequest {
    private String username;

    public Delete(String token, String username) {
        super(token);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
