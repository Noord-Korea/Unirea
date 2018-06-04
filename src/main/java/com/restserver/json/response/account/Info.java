package com.restserver.json.response.account;

public class Info {
    private int id;
    private String username;
    private String email;
    private String token;

    public Info(int id, String token, String username, String email) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
