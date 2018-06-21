package com.server.tick;

import com.dbal.repository.BuildingRepository;
import com.dbal.repository.TownRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.BuildingQueue;
import com.models.Town;
import com.restserver.buildings.resource.models.BaseNormalBuilding;
import com.restserver.buildings.resource.models.BaseResourceBuilding;
import com.restserver.handler.BuildingHandler;
import com.restserver.handler.IBuildingHandler;

import java.util.List;

public class BuildingTick implements Runnable {
    public void update() {
        Logger.getInstance().log("BuildingTick Running", LogLevel.INFORMATION);
        BuildingRepository buildingRepository = new BuildingRepository();
        TownRepository townRepository = new TownRepository();
        List<Town> towns = townRepository.findAllNoDuplicates(null);
        IBuildingHandler buildingHandler = new BuildingHandler();
        for (Town town : towns) {
            if (town.getBuildingQueues().isEmpty()) {
                continue;
            } else {
                List<BuildingQueue> queues = town.getBuildingQueues();
                for (BuildingQueue queue : queues) {
                    if (queue.getValue() == 0){
                        int buildingId = queue.getPk().getBuilding().getId();
                        if (buildingId > 3) {
                            BaseNormalBuilding building = (BaseNormalBuilding) buildingHandler.getNormalTownBuildings(town.getId(), buildingId);
                            building.setBuildingLevel(building.getBuildingLevel() + 1);
                            buildingRepository.save(building);
                        } else {
                            BaseResourceBuilding building = (BaseResourceBuilding) buildingHandler.getResourceBuilding(town.getId(), buildingId);
                            building.setBuildingLevel(building.getBuildingLevel() + 1);
                            buildingRepository.save(building);
                        }
                    }
                    queue.setValue(queue.getValue() - 5);
                }
            }
        }
    }

    @Override
    public void run() {
        Logger.getInstance().log("BuildingTick Running", LogLevel.INFORMATION);

    }
}
