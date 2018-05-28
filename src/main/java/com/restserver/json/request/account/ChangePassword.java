package com.restserver.json.request.account;

public class ChangePassword extends BaseRequest{
    private String newPassword;
    private String verifyPassword;

    public ChangePassword(String token, int id, String newPassword, String verifyPassword) {
        super(token);
        this.newPassword = newPassword;
        this.verifyPassword = verifyPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }
}