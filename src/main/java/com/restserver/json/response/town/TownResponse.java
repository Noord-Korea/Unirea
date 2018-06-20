package com.restserver.json.response.town;

import com.models.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TownResponse {
    private Map<String, Integer> townResources = new HashMap<>();
    private Map<String, Integer> townBuildings = new HashMap<>();
    private int x;
    private int y;
    private String username;
    private String name;

    public TownResponse(Map<String, Integer> townResources, Map<String, Integer> townBuildings, int x, int y, String username, String name) {
        this.townResources = townResources;
        this.townBuildings = townBuildings;
        this.x = x;
        this.y = y;
        this.username = username;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getTownResources() {
        return townResources;
    }

    public void setTownResources(Map<String, Integer> townResources) {
        this.townResources = townResources;
    }

    public Map<String, Integer> getTownBuildings() {
        return townBuildings;
    }

    public void setTownBuildings(Map<String, Integer> townBuildings) {
        this.townBuildings = townBuildings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
