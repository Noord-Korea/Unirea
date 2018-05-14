package com.restserver.utils.accesstoken;

import com.restserver.json.response.Status;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AccessTokenLevel {

    NoLogin(0),
    LoggedIn(1);

    private static final Map<Integer,AccessTokenLevel> lookup
            = new HashMap<Integer,AccessTokenLevel>();

    static {
        for(AccessTokenLevel w : EnumSet.allOf(AccessTokenLevel.class))
            lookup.put(w.getCode(), w);
    }

    private int code;

    private AccessTokenLevel(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static AccessTokenLevel get(int code) {
        return lookup.get(code);
    }
}
