package com.restserver.json.result.account;

public class HolidayReplacement {
    private String username;
    private String usernameHolidayReplacement;

    public HolidayReplacement(String username, String usernameHolidayReplacement) {
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
