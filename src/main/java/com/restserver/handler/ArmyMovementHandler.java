package com.restserver.handler;

import com.dbal.repository.ArmyMovementQueueRepository;
import com.dbal.repository.TownArmyRepository;
import com.dbal.repository.TownRepository;
import com.dbal.repository.TownResourceRepository;
import com.models.*;
import com.restserver.json.request.MoveArmy;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

import java.util.Date;
import java.util.List;

public class ArmyMovementHandler implements IArmyMovementHandler {
    private TownRepository townRepository = new TownRepository();
    private ArmyMovementQueueRepository armyMovementQueueRepository = new ArmyMovementQueueRepository();
    private TownArmyRepository townArmyRepository = new TownArmyRepository();
    private TownResourceRepository townResourceRepository = new TownResourceRepository();

    public Reply moveArmy(MoveArmy moveArmy){

        if (townRepository.findOne(moveArmy.getTownId()) == null || townRepository.findOne(moveArmy.getTargetTownId()) == null){
            return new Reply(Status.NOTFOUND, "Town could not be found");
        }

        Town homeTown = townRepository.findOne(moveArmy.getTownId());
        double distance  = calcDistanceToTarget(moveArmy);
        ArmyMovementQueue queue = new ArmyMovementQueue();
        TownArmyId townArmyPK = new TownArmyId();

        for (TownArmy army : homeTown.getTownArmies()){
            if (army.getArmy().getId() == moveArmy.getArmyId() && army.isInTown()){
                townArmyPK = army.getPk();
                army.setInTown(false);
                townArmyRepository.save(army);
            } else if (!army.isInTown()){
                return new Reply(Status.CONFLICT, "Army not in town");
            }
        }

        queue.setPk(townArmyPK);
        queue.setValue((int) distance);
        queue.setTargetTownId(moveArmy.getTargetTownId());
        queue.setHomeTownId(moveArmy.getTownId());
        queue.setDate(new Date());
        queue.setGoingHome(false);

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

    public double calcDistanceToTargetByTownIds(int homeTownId, int targetTownId){
        Town homeTown = townRepository.findOne(homeTownId);
        Town targetTown = townRepository.findOne(targetTownId);
        return Math.hypot(homeTown.getX() - targetTown.getX(), homeTown.getY() - targetTown.getY());
    }

    public Boolean calcArmyBattle(ArmyMovementQueue queue){
        Town homeTown = townRepository.findOne(queue.getHomeTownId());
        Town targetTown = townRepository.findOne(queue.getTargetTownId());
        TownArmy attackingArmy = null;

        //preparing armies
        for (TownArmy townArmy : homeTown.getTownArmies()){
            if (townArmy.getPk() == queue.getPk()){
                attackingArmy = townArmy;
            }
        }
        List<TownArmy> defendingArmy = targetTown.getTownArmies();

        //preparing buildings that have influence on the battle
        int wallLevel = 0;
        int warehouseLevel = 0;
        for (TownBuilding townBuilding : targetTown.getTownBuildings()){
            if (townBuilding.getBuilding().getId() == 10){
                wallLevel = townBuilding.getLevel();
            } else if (townBuilding.getBuilding().getId() == 5){
                warehouseLevel = townBuilding.getLevel();
            }
        }
        // simulating battle
        for (TownArmy army : defendingArmy){
            if (attackingArmy != null) {
                int defence = army.getValue() * (1 + (wallLevel / 20));
                int result = attackingArmy.getValue() - defence;
                if (result <= 0){
                    townArmyRepository.delete(attackingArmy);
                    army.setValue(Math.abs((result / (1 + (wallLevel /20)))));
                    townArmyRepository.save(army);
                    return false;
                } else {
                    attackingArmy.setValue(result);
                    townArmyRepository.delete(army);
                }
            }

        }

        // if battle is won take resources from enemy town
        int warehouseResourceProtection = warehouseLevel * 40;
        for (TownResources targetResources : targetTown.getTownResources()){
            switch(targetResources.getResource().getName()){
                case "Oil":
                    for (TownResources homeResources: homeTown.getTownResources()){
                        if (homeResources.getResource().getName() == "Oil"){
                            targetResources.setValue(targetResources.getValue() - warehouseResourceProtection);
                            homeResources.setValue(homeResources.getValue() + targetResources.getValue());
                            townResourceRepository.save(homeResources);
                            townResourceRepository.save(targetResources);

                        }
                    }
                    break;
                case "Iron":
                    for (TownResources homeResources: homeTown.getTownResources()){
                        if (homeResources.getResource().getName() == "Iron"){
                            targetResources.setValue(targetResources.getValue() - warehouseResourceProtection);
                            homeResources.setValue(homeResources.getValue() + targetResources.getValue());
                            townResourceRepository.save(homeResources);
                            townResourceRepository.save(targetResources);

                        }
                    }
                    break;
                case "Wood":
                    for (TownResources homeResources: homeTown.getTownResources()){
                        if (homeResources.getResource().getName() == "Wood"){
                            targetResources.setValue(targetResources.getValue() - warehouseResourceProtection);
                            homeResources.setValue(homeResources.getValue() + targetResources.getValue());
                            townResourceRepository.save(homeResources);
                            townResourceRepository.save(targetResources);

                        }
                    }
                    break;
            }
        }
        return true;
    }
}
