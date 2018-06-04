package com.restserver.json.request.account;

public class Login {
    private String email;
    private String password;

    public Login(String email, String password) {
        if (email == null || password == null) {
           throw new IllegalArgumentException();
        }
        this.email = email.toLowerCase();
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
