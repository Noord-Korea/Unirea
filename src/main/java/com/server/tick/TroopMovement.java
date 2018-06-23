package com.server.tick;

import com.dbal.repository.*;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.*;
import com.restserver.handler.BuildingHandler;
import com.restserver.handler.IBuildingHandler;

import java.util.List;

public class TroopMovement implements Runnable {


    public void update() {
        Logger.getInstance().log("Troopmovement Running", LogLevel.INFORMATION);
    }

    @Override
    public void run() {
        Logger.getInstance().log("Troopmovement Running", LogLevel.INFORMATION);

        ArmyMovementQueueRepository armyMovementQueueRepository = new ArmyMovementQueueRepository();
        TownRepository townRepository = new TownRepository();

        List<Town> towns = townRepository.findAllNoDuplicates(null);
        boolean removeQueue = false;

        for (Town town : towns) {
            if (town.getArmyMovementQueues().isEmpty()) {
                continue;
            } else {
                List<ArmyMovementQueue> queues = town.getArmyMovementQueues();
                for (ArmyMovementQueue queue : queues) {
                    if (queue.getValue() <= 0) {
                        armyMovementQueueRepository.delete(queue);
                        removeQueue = true;
                    }
                    queue.setValue(queue.getValue() - 5);
                    if (!removeQueue) {
                        armyMovementQueueRepository.save(queue);
                        removeQueue = false;
                    }
                }
            }
        }
    }
}
