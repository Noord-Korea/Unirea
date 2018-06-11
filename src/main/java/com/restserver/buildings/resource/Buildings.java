package com.restserver.buildings.resource;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Buildings {
    OIL(1),
    IRON(2),
    WOOD(3);

    private static final Map<Integer,Buildings> lookup
            = new HashMap<Integer,Buildings>();

    static {
        for(Buildings w : EnumSet.allOf(Buildings.class))
            lookup.put(w.getCode(), w);
    }

    private int code;

    private Buildings(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static Buildings get(int code) {
        return lookup.get(code);
    }
}