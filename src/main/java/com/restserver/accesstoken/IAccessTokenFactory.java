package com.restserver.accesstoken;

public interface IAccessTokenFactory {
    AccessToken NewToken(String username);

}
