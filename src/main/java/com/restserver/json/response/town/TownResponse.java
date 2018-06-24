package com.restserver.json.response.town;

import java.util.ArrayList;
import java.util.Map;

public class TownResponse {
    private ArrayList<ResourceResponse> townResources;
    private ArrayList<BuildingResponse> townBuildings;
    private int x;
    private int y;
    private String username;
    private String name;
    private int townId;

    public TownResponse(ArrayList<ResourceResponse> townResources, ArrayList<BuildingResponse> townBuildings, int x, int y, String username, String name, int townId) {
        this.townResources = townResources;
        this.townBuildings = townBuildings;
        this.x = x;
        this.y = y;
        this.username = username;
        this.name = name;
        this.townId = townId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public ArrayList<BuildingResponse> getTownBuildings() {
        return townBuildings;
    }

    public void setTownBuildings(ArrayList<BuildingResponse> townBuildings) {
        this.townBuildings = townBuildings;
    }

    public ArrayList<ResourceResponse> getTownResources() {
        return townResources;
    }

    public void setTownResources(ArrayList<ResourceResponse> townResources) {
        this.townResources = townResources;
    }
}
