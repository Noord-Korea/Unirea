package com.restserver.buildings.resource.models;

import com.models.Building;

public abstract class BaseNormalBuilding extends Building {
    private String name;
    private int buildingLevel;

    public BaseNormalBuilding(String name) {
        this.name = name;
    }

    public int getBuildingLevel() {
        return buildingLevel;
    }

    public void setBuildingLevel(int buildingLevel) {
        this.buildingLevel = buildingLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
