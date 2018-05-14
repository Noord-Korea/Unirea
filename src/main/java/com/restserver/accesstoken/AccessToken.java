package com.restserver.accesstoken;

import java.util.Date;

public class AccessToken {

    private String accessToken;
    private int expiresIn;
    private String username;
    private Date issued;
    private Date expires;


    public AccessToken(String accessToken, int expiresIn, String username, Date issued, Date expires) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.username = username;
        this.issued = issued;
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public Date getIssued() {
        return issued;
    }

    public Date getExpires() {
        return expires;
    }
}
