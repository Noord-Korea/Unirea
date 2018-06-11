package com.restserver.handler;

import com.dbal.repository.BuildingRepository;
import com.dbal.repository.TownBuildingRepository;
import com.dbal.repository.TownRepository;
import com.models.Building;
import com.models.Town;
import com.models.TownBuilding;
import com.restserver.buildings.resource.ResourceType;
import com.restserver.buildings.resource.models.IronBuilding;
import com.restserver.buildings.resource.models.OilBuilding;
import com.restserver.buildings.resource.models.WoodBuilding;

import java.util.Set;

public class BuildingHandler {

    TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
    BuildingRepository buildingRepository = new BuildingRepository();
    TownRepository townRepository = new TownRepository();
    
    public Building getResourceBuilding(int townId, int buildingId){
        
        Town town = townRepository.findOne(townId);
        if (town == null){
            return null;
        }
        Set<TownBuilding> townBuildings = town.getTownBuildings();
        
        switch (buildingId){
            //OIL
            case 1:
                OilBuilding oilBuilding = new OilBuilding();
                for (TownBuilding townBuilding : townBuildings){
                    if(townBuilding.getBuilding().getId() == 1){
                        oilBuilding.setName(townBuilding.getBuilding().getName());
                        oilBuilding.setBuildingLevel(townBuilding.getLevel());
                        oilBuilding.setResourceType(ResourceType.OIL);
                    }
                }
                return oilBuilding;
            //IRON
            case 2:
                IronBuilding ironBuilding = new IronBuilding();
                for (TownBuilding townBuilding : townBuildings){
                    if(townBuilding.getBuilding().getId() == 2){
                        ironBuilding.setName(townBuilding.getBuilding().getName());
                        ironBuilding.setBuildingLevel(townBuilding.getLevel());
                        ironBuilding.setResourceType(ResourceType.OIL);
                    }
                }
                return ironBuilding;
            //WOOD
            case 3:
                WoodBuilding woodBuilding = new WoodBuilding();
                for (TownBuilding townBuilding : townBuildings){
                    if(townBuilding.getBuilding().getId() == 3){
                        woodBuilding.setName(townBuilding.getBuilding().getName());
                        woodBuilding.setBuildingLevel(townBuilding.getLevel());
                        woodBuilding.setResourceType(ResourceType.OIL);
                    }
                }
                return woodBuilding;
        }
        return null;
    }

}
