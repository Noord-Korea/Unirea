package com.restserver.json.request.account;

public class Delete extends BaseRequest{
    private String username;

    public Delete(String username) {
        this.username = username;
    }
}
