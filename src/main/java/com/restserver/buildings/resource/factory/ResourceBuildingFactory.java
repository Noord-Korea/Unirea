package com.restserver.buildings.resource.factory;

import com.models.Building;
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

    @Override
    public IResourceBuilding getByBuildingId(int id) {
        switch (id){
            //OIL
            case 1:
                return new OilBuilding();
            //IRON
            case 2:
                return new IronBuilding();
            //WOOD
            case 3:
                return new WoodBuilding();
        }
        return null;
    }

    @Override
    public IResourceBuilding getByBuilding(Building building) {
        if(building == null) return null;
        return getByBuildingId(building.getId());
    }
}
