package com.server.tick;

import com.dbal.repository.TownRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.Town;

import java.util.List;

public class BuildingTick implements Runnable {
    public void update() {
        Logger.getInstance().log("BuildingTick Running", LogLevel.INFORMATION);
        TownRepository townRepository = new TownRepository();
        List<Town> towns = townRepository.findAllNoDuplicates(null);
        for (Town town : towns) {
            if(town.getBuildingQueues().isEmpty()){
                continue;
            }
            else {

            }
        }
    }

    @Override
    public void run() {
        Logger.getInstance().log("BuildingTick Running", LogLevel.INFORMATION);

    }
}
