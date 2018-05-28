package com.restserver.utils.accesstoken;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AccessTokenLevel {

    NOLOGIN(0),
    LOGGEDIN(1);

    private static final Map<Integer,AccessTokenLevel> lookup
            = new HashMap<>();

    static {
        for(AccessTokenLevel w : EnumSet.allOf(AccessTokenLevel.class))
            lookup.put(w.getCode(), w);
    }

    private int code;

    AccessTokenLevel(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static AccessTokenLevel get(int code) {
        return lookup.get(code);
    }
}
