package com.server.tick;

import com.dbal.repository.BuildingQueueRepository;
import com.dbal.repository.BuildingRepository;
import com.dbal.repository.TownBuildingRepository;
import com.dbal.repository.TownRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.BuildingQueue;
import com.models.Town;
import com.models.TownBuilding;
import com.restserver.buildings.resource.models.BaseNormalBuilding;
import com.restserver.buildings.resource.models.BaseResourceBuilding;
import com.restserver.handler.BuildingHandler;
import com.restserver.handler.IBuildingHandler;

import java.util.List;

public class BuildingTick implements Runnable {
    public void run() {
        Logger.getInstance().log("BuildingTick Running", LogLevel.INFORMATION);
        BuildingRepository buildingRepository = new BuildingRepository();
        BuildingQueueRepository buildingQueueRepository = new BuildingQueueRepository();
        TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
        TownRepository townRepository = new TownRepository();
        List<Town> towns = townRepository.findAllNoDuplicates(null);
        IBuildingHandler buildingHandler = new BuildingHandler();
        boolean removeQueue = false;
        for (Town town : towns) {
            if (town.getBuildingQueues().isEmpty()) {
                continue;
            } else {
                List<BuildingQueue> queues = town.getBuildingQueues();
                for (BuildingQueue queue : queues) {
                    if (queue.getValue() <= 0){
                        int buildingId = queue.getPk().getBuilding().getId();
                        if (buildingId > 3) {
                            for (TownBuilding townBuilding : town.getTownBuildings()){
                                if (townBuilding.getBuilding().getId() == buildingId){
                                    townBuilding.setLevel(townBuilding.getLevel() + 1);
                                    townBuildingRepository.save(townBuilding);

                                }
                            }
                        } else {
                            for (TownBuilding townBuilding : town.getTownBuildings()){
                                if (townBuilding.getBuilding().getId() == buildingId){
                                    townBuilding.setLevel(townBuilding.getLevel() + 1);
                                    townBuildingRepository.save(townBuilding);
                                }
                            }
                        }
                        buildingQueueRepository.delete(queue);
                        removeQueue = true;
                    }
                    queue.setValue(queue.getValue() - 5);
                    if (!removeQueue){
                        buildingQueueRepository.save(queue);
                    }
                }
            }
        }
    }
}
