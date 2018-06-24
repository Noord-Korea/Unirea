package com.restserver.json.response.town;

import java.util.ArrayList;

public class BuildingResponse {
    private int id;
    private String name;
    private int level;
    private ArrayList<ResourceResponse> upgradeCost;
    private int resourceProduction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<ResourceResponse> getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(ArrayList<ResourceResponse> upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }

    public void setResourceProduction(int resourceProduction) {
        this.resourceProduction = resourceProduction;
    }
}
