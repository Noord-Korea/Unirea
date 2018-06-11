package com.restserver.json.response;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Status {
    OK(200),
    ERROR(400),
    NOAUTH(401),
    NOACCESS(403),
    NOTFOUND(404),
    CONFLICT(409);

    private static final Map<Integer,Status> lookup
            = new HashMap<>();

    static {
        for(Status w : EnumSet.allOf(Status.class))
            lookup.put(w.getCode(), w);
    }

    private int code;

    private Status(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static Status get(int code) {
        return lookup.get(code);
    }
}
