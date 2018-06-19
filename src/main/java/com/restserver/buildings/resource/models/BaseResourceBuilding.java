package com.restserver.buildings.resource.models;

import com.models.Building;
import com.restserver.buildings.resource.ResourceType;

public abstract class BaseResourceBuilding extends Building implements IResourceBuilding{
    private String name;
    private ResourceType resourceType;
    private int buildingLevel;
    private int resourceProduction;

    BaseResourceBuilding(String name, ResourceType resourceType) {
        this.name = name;
        this.resourceType = resourceType;
    }

    @Override
    public String getName() {
        return name;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getBuildingLevel() {
        return buildingLevel;
    }

    public void setBuildingLevel(int buildingLevel) {
        this.buildingLevel = buildingLevel;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }

    public void setResourceProduction(int resourceProduction) {
        this.resourceProduction = resourceProduction;
    }
}
