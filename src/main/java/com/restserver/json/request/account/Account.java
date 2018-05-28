package com.restserver.json.request.account;

import com.models.AccessToken;

public class Account extends BaseRequest{
    private String username;
    private String email;
    private int id;

    public Account(String token, String username, String email, String password, int id) {
        super(token);
        this.username = username;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
