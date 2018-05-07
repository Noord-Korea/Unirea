package com.restserver.json.result.account;

public class AccessToken {

    private String AccessToken;
    private int ExpiresIn;
    private String Username;
    private String Issued;
    private String Expires;

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public int getExpiresIn() {
        return ExpiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        ExpiresIn = expiresIn;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getIssued() {
        return Issued;
    }

    public void setIssued(String issued) {
        Issued = issued;
    }

    public String getExpires() {
        return Expires;
    }

    public void setExpires(String expires) {
        Expires = expires;
    }
}
