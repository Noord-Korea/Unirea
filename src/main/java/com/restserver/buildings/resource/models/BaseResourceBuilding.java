package com.restserver.buildings.resource.models;

import com.models.Building;
import com.restserver.buildings.resource.ResourceType;

public abstract class BaseResourceBuilding extends Building implements IResourceBuilding{
    private String name;
    private ResourceType resourceType;

    public BaseResourceBuilding(String name, ResourceType resourceType) {
        this.name = name;
        this.resourceType = resourceType;
    }


    public String getName() {
        return name;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
