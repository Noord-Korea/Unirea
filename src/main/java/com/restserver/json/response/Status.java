package com.restserver.json.response;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Status {
    Ok(200),
    Error(400),
    NoAuth(401),
    NoAccess(403),
    NotFound(404);

    private static final Map<Integer,Status> lookup
            = new HashMap<Integer,Status>();

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
