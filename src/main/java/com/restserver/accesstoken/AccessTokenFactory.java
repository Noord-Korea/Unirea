package com.restserver.accesstoken;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AccessTokenFactory implements IAccessTokenFactory {
    @Override
    public AccessToken newToken(String username) {
        return new AccessToken(generateUUID(),20,username, new Date(System.currentTimeMillis()), generateExpirationDate());
    }

    private String generateUUID(){
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println("uuid = " + uuid);
        return uuid;
    }

    private Date generateExpirationDate(){
        long expirationTime = TimeUnit.MILLISECONDS.convert(20, TimeUnit.MINUTES);
        Date expiry = new Date(System.currentTimeMillis() + expirationTime);
        return expiry;
    }
}
