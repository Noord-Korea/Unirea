package com.server.tick;

import com.dbal.repository.*;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.*;
import com.restserver.handler.ArmyMovementHandler;
import com.restserver.handler.BuildingHandler;
import com.restserver.handler.IArmyMovementHandler;
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

        IArmyMovementHandler armyMovementHandler = new ArmyMovementHandler();

        List<Town> towns = townRepository.findAllNoDuplicates(null);
        boolean removeQueue;

        for (Town town : towns) {
            if (town.getArmyMovementQueues().isEmpty()) {
                continue;
            } else {
                List<ArmyMovementQueue> queues = town.getArmyMovementQueues();
                for (ArmyMovementQueue queue : queues) {
                    removeQueue = false;
                    if (queue.getValue() <= 0) {
                        if (queue.isGoingHome()){
                            armyMovementQueueRepository.delete(queue);
                            removeQueue = true;
                        } else {
                            armyMovementHandler.calcArmyBattle(queue);
                            int distance = (int) armyMovementHandler.calcDistanceToTargetByTownIds(queue.getHomeTownId(),queue.getTargetTownId());
                            queue.setValue(distance);
                            queue.setGoingHome(true);
                            armyMovementQueueRepository.save(queue);
                        }
                    }
                    queue.setValue(queue.getValue() - 5);
                    if (!removeQueue) {
                        armyMovementQueueRepository.save(queue);
                    }
                }
            }
        }
    }
}
