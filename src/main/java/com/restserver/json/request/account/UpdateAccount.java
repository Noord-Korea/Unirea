package com.restserver.json.request.account;

import com.models.AccessToken;

public class UpdateAccount extends BaseRequest{
    private String username;
    private String email;
    private String password;

    public UpdateAccount(AccessToken token, String username, String email, String password) {
        super(token);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
