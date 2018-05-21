package com.restserver.json.request.account;

import com.models.AccessToken;

public class ChangePassword extends BaseRequest{
    private String username;
    private String email;
    private String newPassword;
    private String verifyPassword;

    public ChangePassword(AccessToken token, String username, String email, String newPassword, String verifyPassword) {
        super(token);
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