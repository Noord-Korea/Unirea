package com.restserver.buildings.resource;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ResourceType {
    OIL(1),
    IRON(2),
    WOOD(3);

    private static final Map<Integer, ResourceType> lookup
            = new HashMap<>();

    static {
        for (ResourceType w : EnumSet.allOf(ResourceType.class))
            lookup.put(w.getCode(), w);
    }

    private int code;

    private ResourceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ResourceType get(int code) {
        return lookup.get(code);
    }
}