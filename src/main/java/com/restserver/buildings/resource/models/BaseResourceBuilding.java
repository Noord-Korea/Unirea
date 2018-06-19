package com.restserver.buildings.resource.models;

import com.models.Building;
import com.restserver.buildings.resource.Buildings;
import com.restserver.buildings.resource.ResourceType;

public abstract class BaseResourceBuilding extends Building implements IResourceBuilding{
    private String name;
    private int resourceType;
    private int buildingLevel;
    private int resourceProduction;


    BaseResourceBuilding(String name, int resourceType) {
        this.name = name;
        this.resourceType = resourceType;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getResourceType() {
        return resourceType;
    }

    public int getBuildingLevel() {
        return buildingLevel;
    }

    public void setBuildingLevel(int buildingLevel) {
        this.buildingLevel = buildingLevel;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }

    public void setResourceProduction(int resourceProduction) {
        this.resourceProduction = resourceProduction;
    }
}
