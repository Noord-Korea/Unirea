package com.restserver.buildings.resource.models;

import com.restserver.buildings.resource.ResourceType;

public class WoodBuilding extends BaseResourceBuilding {
    public WoodBuilding(String name, ResourceType resourceType, int level, int resourceProduction) {
        super(name, resourceType, level, resourceProduction);
    }
}
