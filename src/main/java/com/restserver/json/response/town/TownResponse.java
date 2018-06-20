package com.restserver.json.response.town;

import com.models.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TownResponse {
    private Map<String, Integer> townResources;
    private Map<String, Integer> townBuildings;
    private int x;
    private int y;
    private Player player;
    private String name;

    public TownResponse(Map<String, Integer> townResources, Map<String, Integer> townBuildings, int x, int y, Player player, String name) {
        this.townResources = townResources;
        this.townBuildings = townBuildings;
        this.x = x;
        this.y = y;
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
}
