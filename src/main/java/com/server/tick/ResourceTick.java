package com.server.tick;

import com.dbal.repository.ResourceRepository;
import com.dbal.repository.TownRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.Building;
import com.models.Town;
import com.models.TownResources;
import com.restserver.buildings.resource.Buildings;
import com.restserver.buildings.resource.models.IResourceBuilding;
import com.restserver.buildings.resource.models.OilBuilding;
import com.restserver.handler.BuildingHandler;

import java.util.List;

public class ResourceTick implements Runnable{
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
        TownRepository townRepository = new TownRepository();

        List<Town> towns = townRepository.findAll();

        for (int i = 0; i < towns.size(); i++) {
            Town town = towns.get(i);
            OilBuilding building = (OilBuilding) buildingHandler.getResourceBuilding(town.getId(), Buildings.OIL.getCode());
        }

        Logger.getInstance().log("Got Towns", LogLevel.DEBUG);

    }
}
