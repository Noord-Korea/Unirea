package com.restserver.buildings.resource.models;

import com.restserver.buildings.resource.ResourceType;

public class OilBuilding extends BaseResourceBuilding {
    public OilBuilding() {
        super("Oil Refinery", ResourceType.OIL.getCode());
    }
}
