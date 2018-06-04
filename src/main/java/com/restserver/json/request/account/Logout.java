package com.restserver.json.request.account;

public class Logout extends BaseRequest{
    private String username;

    public Logout(String token, String username) {
        super(token);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
