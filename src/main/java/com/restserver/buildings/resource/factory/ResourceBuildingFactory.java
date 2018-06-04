package com.restserver.buildings.resource.factory;

import com.restserver.buildings.resource.ResourceType;
import com.restserver.buildings.resource.models.IResourceBuilding;
import com.restserver.buildings.resource.models.IronBuilding;
import com.restserver.buildings.resource.models.OilBuilding;
import com.restserver.buildings.resource.models.WoodBuilding;

public class ResourceBuildingFactory implements IResourceBuildingFactory {
    @Override
    public IResourceBuilding createBuilding() {
        return null;
    }

    public IronBuilding createIronBuilding(){
        return new IronBuilding();
    }

    public OilBuilding createOilBuilding(){
        return new OilBuilding();
    }

    public WoodBuilding createWoodBuilding(){
        return new WoodBuilding();
    }
}
