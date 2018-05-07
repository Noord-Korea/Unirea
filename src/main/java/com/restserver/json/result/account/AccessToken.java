package com.restserver.json.result.account;

public class AccessToken {

    private String AccessToken;
    private int ExpiresIn;
    private String Username;
    private String Issued;
    private String Expires;


    public AccessToken(String accessToken, int expiresIn, String username, String issued, String expires) {
        AccessToken = accessToken;
        ExpiresIn = expiresIn;
        Username = username;
        Issued = issued;
        Expires = expires;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public int getExpiresIn() {
        return ExpiresIn;
    }

    public String getUsername() {
        return Username;
    }

    public String getIssued() {
        return Issued;
    }

    public String getExpires() {
        return Expires;
    }
}
