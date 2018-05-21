package com.restserver.json.request.account;

import com.models.AccessToken;

public class HolidayReplacement extends BaseRequest{
    private String username;
    private String usernameHolidayReplacement;

    public HolidayReplacement(AccessToken token, String username, String usernameHolidayReplacement) {
        super(token);
        this.username = username;
        this.usernameHolidayReplacement = usernameHolidayReplacement;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameHolidayReplacement() {
        return usernameHolidayReplacement;
    }
}
