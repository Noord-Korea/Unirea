package com.server.tick;

import com.dbal.repository.ResourceRepository;
import com.dbal.repository.TownRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.Town;
import com.restserver.buildings.resource.ResourceType;
import com.restserver.buildings.resource.models.OilBuilding;
import com.restserver.handler.BuildingHandler;

import java.util.List;

public class ResourceTick implements Runnable {
    private ResourceRepository resourceRepository;
    private TownRepository townRepository;

    public ResourceTick() {
        resourceRepository = new ResourceRepository();
        townRepository = new TownRepository();
    }

    @Override
    public void run() {
        Logger.getInstance().log("RecruitingTick Running", LogLevel.INFORMATION);

        BuildingHandler buildingHandler = new BuildingHandler();
        townRepository = new TownRepository();

        List<Town> towns = townRepository.findAll();

        for (int i = 0; i < towns.size(); i++) {
            Town town = towns.get(i);
            OilBuilding building = (OilBuilding) buildingHandler.getResourceBuilding(town.getId(), ResourceType.OIL.getCode());
            int production = building.getResourceProduction();
            //town.addResource(building.);
            //building.getResourceProduction();
        }

        Logger.getInstance().log("Got Towns", LogLevel.DEBUG);

    }
}
