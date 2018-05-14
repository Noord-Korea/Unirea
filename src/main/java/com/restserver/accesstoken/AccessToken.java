package com.restserver.accesstoken;

import com.models.Player;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AccessToken {

    private String accessToken;
    private int expiresIn;
    private String username;
    private Date issued;
    private Date expires;


    public AccessToken(String accessToken, int expiresIn, Player player, Date issued, Date expires) {
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

    public void refresh(){
        long expirationTime = TimeUnit.MILLISECONDS.convert(20, TimeUnit.MINUTES);
        this.expires = new Date(System.currentTimeMillis() + expirationTime);
    }
}
