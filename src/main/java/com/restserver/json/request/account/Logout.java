package com.restserver.json.request.account;

public class Logout {
    private String username;

    public Logout(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}