package com.restserver.accesstoken;

public interface IAccessTokenFactory {
    AccessToken newToken(String username);

}
