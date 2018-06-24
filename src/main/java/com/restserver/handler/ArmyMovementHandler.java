package com.restserver.handler;

import com.dbal.repository.ArmyMovementQueueRepository;
import com.dbal.repository.TownArmyRepository;
import com.dbal.repository.TownRepository;
import com.dbal.repository.TownResourceRepository;
import com.dbal.specification.ArmyMovementQueueSpecification;
import com.dbal.specification.TownArmySpecification;
import com.models.*;
import com.restserver.json.request.MoveArmy;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

import java.util.ArrayList;
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
        if (armyMovementQueueRepository.findOne(ArmyMovementQueueSpecification.getByHomeTownId(moveArmy.getTownId())) != null){
            return new Reply(Status.CONFLICT, "You are already sending troops somewhere");
        }

        Town homeTown = townRepository.findOne(moveArmy.getTownId());
        double distance  = calcDistanceToTarget(moveArmy);
        ArmyMovementQueue queue = new ArmyMovementQueue();
        TownArmyId infantryPk = null;
        TownArmyId cavalryPk = null;
        TownArmyId armoredPk = null;
        for (TownArmy army : homeTown.getTownArmies()){
            if (army.getArmy().getName().equals("Infantry") && army.getValue() >= moveArmy.getTroopAmount().get(1)){
                army.setInTown(army.getValue() - moveArmy.getTroopAmount().get(1));
                infantryPk = army.getPk();
            } else if (army.getArmy().getName().equals("Cavalry") && army.getValue() >= moveArmy.getTroopAmount().get(2)){
                army.setInTown(army.getValue() - moveArmy.getTroopAmount().get(2));
                cavalryPk = army.getPk();
            } else if (army.getArmy().getName().equals("Armored") && army.getValue() >= moveArmy.getTroopAmount().get(3)){
                army.setInTown(army.getValue() - moveArmy.getTroopAmount().get(3));
                armoredPk = army.getPk();
            }
            else {
                return new Reply(Status.CONFLICT, "Not enough troops in town");
            }
        }
        townArmyRepository.save(homeTown.getTownArmies());

        // add pk's here
        queue.setInfantryPk(infantryPk);
        queue.setCavalryPk(cavalryPk);
        queue.setArmoredPk(armoredPk);
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

        //preparing armies
        List<TownArmy> attackingArmy = new ArrayList<>();
        attackingArmy.add(townArmyRepository.findOne(TownArmySpecification.getByArmyPk(queue.getInfantryPk())));
        attackingArmy.add(townArmyRepository.findOne(TownArmySpecification.getByArmyPk(queue.getCavalryPk())));
        attackingArmy.add(townArmyRepository.findOne(TownArmySpecification.getByArmyPk(queue.getArmoredPk())));
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
            int id = army.getArmy().getId() - 1;
            int defence = army.getInTown() * (1 + (wallLevel / 20));
            int result = (attackingArmy.get(id).getValue() - attackingArmy.get(id).getInTown()) - defence;
            if (result <= 0){
                townArmyRepository.delete(attackingArmy.get(id));
                army.setValue((army.getValue() - army.getInTown()) + Math.abs((result / (1 + (wallLevel /20)))));
                townArmyRepository.save(army);
                return false;
            } else {
                attackingArmy.get(id).setValue(result + attackingArmy.get(id).getInTown());
                townArmyRepository.save(attackingArmy.get(id));
                townArmyRepository.delete(army);
            }
        }

        // if battle is won take resources from enemy town
        int warehouseResourceProtection = warehouseLevel * 40;
        for (TownResources targetResources : targetTown.getTownResources()){
            switch(targetResources.getResource().getName()){
                case "Oil":
                    for (TownResources homeResources: homeTown.getTownResources()){
                        if (homeResources.getResource().getName().equals("Oil")){
                            targetResources.setValue(targetResources.getValue() - warehouseResourceProtection);
                            homeResources.setValue(homeResources.getValue() + targetResources.getValue());
                            townResourceRepository.save(homeResources);
                            townResourceRepository.save(targetResources);

                        }
                    }
                    break;
                case "Iron":
                    for (TownResources homeResources: homeTown.getTownResources()){
                        if (homeResources.getResource().getName().equals("Iron")){
                            targetResources.setValue(targetResources.getValue() - warehouseResourceProtection);
                            homeResources.setValue(homeResources.getValue() + targetResources.getValue());
                            townResourceRepository.save(homeResources);
                            townResourceRepository.save(targetResources);

                        }
                    }
                    break;
                case "Wood":
                    for (TownResources homeResources: homeTown.getTownResources()){
                        if (homeResources.getResource().getName().equals("Wood")){
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

    public void updateTroopsInTown(ArmyMovementQueue queue){
        List<TownArmy> townArmy = new ArrayList<>();
        townArmy.add(townArmyRepository.findOne(TownArmySpecification.getByArmyPk(queue.getInfantryPk())));
        townArmy.add(townArmyRepository.findOne(TownArmySpecification.getByArmyPk(queue.getCavalryPk())));
        townArmy.add(townArmyRepository.findOne(TownArmySpecification.getByArmyPk(queue.getArmoredPk())));
        for (TownArmy army : townArmy){
            army.setInTown(army.getValue());
        }
        townArmyRepository.save(townArmy);
    }
}
