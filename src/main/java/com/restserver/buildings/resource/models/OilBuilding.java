package com.restserver.buildings.resource.models;

import com.restserver.buildings.resource.ResourceType;

public class OilBuilding extends BaseResourceBuilding {
    public OilBuilding(String name, ResourceType resourceType, int level, int resourceProduction) {
        super(name, resourceType, level, resourceProduction);
    }
}
