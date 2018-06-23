package com.restserver.handler;

import com.dbal.repository.ArmyMovementQueueRepository;
import com.dbal.repository.TownRepository;
import com.models.ArmyMovementQueue;
import com.models.Town;
import com.models.TownArmy;
import com.models.TownArmyId;
import com.restserver.json.request.MoveArmy;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

import java.util.Date;
import java.util.List;

public class ArmyMovementHandler {
    private TownRepository townRepository = new TownRepository();
    private ArmyMovementQueueRepository armyMovementQueueRepository = new ArmyMovementQueueRepository();

    public Reply MoveArmy(MoveArmy moveArmy){

        if (townRepository.findOne(moveArmy.getTownId()) == null || townRepository.findOne(moveArmy.getTargetTownId()) == null){
            return new Reply(Status.NOTFOUND, "Town could not be found");
        }

        Town homeTown = townRepository.findOne(moveArmy.getTownId());
        double distance  = calcDistanceToTarget(moveArmy);
        ArmyMovementQueue queue = new ArmyMovementQueue();
        TownArmyId townArmyPK = new TownArmyId();

        for (TownArmy army : homeTown.getTownArmies()){
            if (army.getArmy().getId() == moveArmy.getArmyId()){
                townArmyPK = army.getPk();
            }
        }

        queue.setPk(townArmyPK);
        queue.setValue((int) distance);
        queue.setTargetTownId(moveArmy.getTargetTownId());
        queue.setHomeTownId(moveArmy.getTownId());
        queue.setDate(new Date());

        List<ArmyMovementQueue> queues = homeTown.getArmyMovementQueues();
        queues.add(queue);
        armyMovementQueueRepository.save(queues);

        return new Reply(Status.OK, "Troops have been sent");
    }

    private double calcDistanceToTarget(MoveArmy moveArmy){
        Town homeTown = townRepository.findOne(moveArmy.getTownId());
        Town targetTown = townRepository.findOne(moveArmy.getTargetTownId());
        return Math.hypot(homeTown.getX() - targetTown.getX(), homeTown.getY() - targetTown.getY());
    }
}
