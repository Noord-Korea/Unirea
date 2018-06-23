package com.server.tick;

import com.dbal.repository.ArmyMovementQueueRepository;
import com.dbal.repository.TownRepository;
import com.logging.LogLevel;
import com.logging.Logger;
import com.models.ArmyMovementQueue;
import com.models.Town;
import com.restserver.handler.ArmyMovementHandler;
import com.restserver.handler.IArmyMovementHandler;

import java.util.List;

public class TroopMovement implements Runnable {

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
                            armyMovementHandler.updateTroopsInTown(queue);
                            armyMovementQueueRepository.delete(queue);
                            removeQueue = true;
                        } else {
                            if (armyMovementHandler.calcArmyBattle(queue)){
                                int distance = (int) armyMovementHandler.calcDistanceToTargetByTownIds(queue.getHomeTownId(),queue.getTargetTownId());
                                queue.setValue(distance);
                                queue.setGoingHome(true);
                                armyMovementQueueRepository.save(queue);
                            } else {
                                armyMovementQueueRepository.delete(queue);
                                removeQueue = true;
                            }

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
