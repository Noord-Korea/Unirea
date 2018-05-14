package com.restserver.json.request.account;

import com.restserver.json.request.IResult;

public class Register implements IResult {
    private String email;
    private String username;
    private String password;

    public Register(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
