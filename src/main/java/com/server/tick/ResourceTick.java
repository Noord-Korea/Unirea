package com.server.tick;

import com.dbal.repository.ResourceRepository;
import com.dbal.repository.TownRepository;
import com.dbal.repository.TownResourceRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.Town;
import com.models.TownBuilding;
import com.models.TownResources;
import com.restserver.buildings.resource.ResourceType;
import com.restserver.buildings.resource.models.BaseResourceBuilding;
import com.restserver.buildings.resource.models.IronBuilding;
import com.restserver.buildings.resource.models.OilBuilding;
import com.restserver.buildings.resource.models.WoodBuilding;
import com.restserver.handler.BuildingHandler;
import com.restserver.handler.IBuildingHandler;

import java.util.List;

public class ResourceTick implements Runnable {
    private ResourceRepository resourceRepository;
    private TownRepository townRepository;
    private TownResourceRepository townResourceRepository;

    public ResourceTick() {
        resourceRepository = new ResourceRepository();
        townRepository = new TownRepository();
        townResourceRepository = new TownResourceRepository();
    }

    @Override
    public void run() {
        Logger.getInstance().log("ResourceTick Running", LogLevel.INFORMATION);

        IBuildingHandler buildingHandler = new BuildingHandler();
        townRepository = new TownRepository();

        List<Town> towns = townRepository.findAllNoDuplicates(null);

        for (int i = 0; i < towns.size(); i++) {
            Town town = towns.get(i);

            OilBuilding oilBuilding = null;
            IronBuilding ironBuilding = null;
            WoodBuilding woodBuilding = null;

            //SET oil, iron and wood buildings
            for (TownBuilding townBuilding : town.getTownBuildings()) {
                switch (townBuilding.getBuilding().getId()) {
                    case 1:
                        oilBuilding = (OilBuilding) buildingHandler.getResourceBuilding(town.getId(), ResourceType.OIL.getCode());
                        oilBuilding.setBuildingLevel(townBuilding.getLevel());
                        break;
                    case 2:
                        ironBuilding = (IronBuilding) buildingHandler.getResourceBuilding(town.getId(), ResourceType.IRON.getCode());
                        ironBuilding.setBuildingLevel(townBuilding.getLevel());
                        break;
                    case 3:
                        woodBuilding = (WoodBuilding) buildingHandler.getResourceBuilding(town.getId(), ResourceType.WOOD.getCode());
                        woodBuilding.setBuildingLevel(townBuilding.getLevel());
                        break;
                }
            }

            for (TownResources townResource : town.getTownResources()) {
                BaseResourceBuilding resourceBuilding = null;
                switch (townResource.getResource().getId()){
                    case 1:
                        townResource.addResource((int) oilBuilding.getResourceProduction());
                        break;
                    case 2:
                        townResource.addResource((int) ironBuilding.getResourceProduction());
                        break;
                    case 3:
                        townResource.addResource((int) woodBuilding.getResourceProduction());
                        break;
                }
                townResourceRepository.save(townResource);
            }
        }

        Logger.getInstance().log("Got Towns", LogLevel.DEBUG);

    }
}
