package com.restserver.json.request.account;

import com.models.AccessToken;

public class ChangePassword extends BaseRequest{
    private int id;
    private String newPassword;
    private String verifyPassword;

    public ChangePassword(AccessToken token, int id, String newPassword, String verifyPassword) {
        super(token);
        this.id = id;
        this.newPassword = newPassword;
        this.verifyPassword = verifyPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public int getId() {
        return id;
    }
}