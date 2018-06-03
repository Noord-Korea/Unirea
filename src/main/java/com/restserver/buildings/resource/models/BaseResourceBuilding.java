package com.restserver.buildings.resource.models;

import com.restserver.buildings.resource.ResourceType;

public abstract class BaseResourceBuilding implements IResourceBuilding{
    private String name;
    private ResourceType resourceType;
    private int level;
    private int resourceProduction;

    public BaseResourceBuilding(String name, ResourceType resourceType, int level, int resourceProduction) {
        this.name = name;
        this.resourceType = resourceType;
        this.level = level;
        this.resourceProduction = resourceProduction;
    }


    public String getName() {
        return name;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }

    public void setResourceProduction(int resourceProduction) {
        this.resourceProduction = resourceProduction;
    }
}
