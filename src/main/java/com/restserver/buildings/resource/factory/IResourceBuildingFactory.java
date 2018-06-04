package com.restserver.buildings.resource.factory;

import com.restserver.buildings.resource.models.IResourceBuilding;
import com.restserver.buildings.resource.models.IronBuilding;
import com.restserver.buildings.resource.models.OilBuilding;
import com.restserver.buildings.resource.models.WoodBuilding;

public interface IResourceBuildingFactory {
    IResourceBuilding createBuilding();
    IronBuilding createIronBuilding();
    OilBuilding createOilBuilding();
    WoodBuilding createWoodBuilding();
}
