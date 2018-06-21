package com.server.tick;

import com.dbal.repository.TownRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.ArmyQueue;
import com.models.BuildingQueue;
import com.models.Town;
import com.models.TownArmy;
import com.restserver.handler.BuildingHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecruitingTick implements Runnable {
    public void update() {
        Logger.getInstance().log("RecruitingTick Running", LogLevel.INFORMATION);
        TownRepository townRepository = new TownRepository();
        List<Town> towns = townRepository.findAllNoDuplicates(null);
        BuildingHandler buildingHandler = new BuildingHandler();
        for (Town town : towns) {
            if (town.getArmyQueues().isEmpty()) {
                continue;
            } else {
                Set<ArmyQueue> queues = town.getArmyQueues();
                for (ArmyQueue queue : queues) {
                    if (queue.getValue() == 0){
                        Set<TownArmy> army = new HashSet<>();
                    }
                    queue.setValue(queue.getValue() - 5);
                }
            }
        }
    }

    @Override
    public void run() {
        Logger.getInstance().log("RecruitingTick Running", LogLevel.INFORMATION);

    }
}
