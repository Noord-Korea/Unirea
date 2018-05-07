package com.restserver.json.result.account;

public class ChangePassword {
    private String username;
    private String email;
    private String newPassword;
    private String verifyPassword;

    public ChangePassword(String username, String email, String newPassword, String verifyPassword) {
        this.username = username;
        this.email = email;
        this.newPassword = newPassword;
        this.verifyPassword = verifyPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }
}