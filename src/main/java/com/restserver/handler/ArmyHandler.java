package com.restserver.handler;

import com.dbal.repository.ArmyRepository;
import com.dbal.repository.TownArmyRepository;
import com.dbal.repository.TownRepository;
import com.dbal.specification.ArmySpecification;
import com.models.Army;
import com.models.Town;
import com.models.TownArmy;
import com.models.TownResources;
import com.restserver.json.request.army.TrainArmy;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

import java.util.List;

public class ArmyHandler {

    public Reply trainArmy(TrainArmy trainArmy) {
        TownArmy townArmy = new TownArmy();
        ArmyRepository armyRepository = new ArmyRepository();

        Army army = armyRepository.findOne(ArmySpecification.getByArmyID(trainArmy.getArmy()));
        townArmy.setArmy(army);

        TownRepository townRepository = new TownRepository();
        Town town = townRepository.findOne(trainArmy.getTownId());
        if (town == null) {
            return new Reply(Status.ERROR, "something went wrong");
        }
        if (enoughResources(town, army)) {
            townArmy.setTown(town);
            townArmy.setValue(townArmy.getValue() + trainArmy.getValue());
            TownArmyRepository townArmyRepository = new TownArmyRepository();
            townArmyRepository.save(townArmy);
            return new Reply(Status.OK, "succeeded");
        }
        return new Reply(Status.CONFLICT, "not enough resources");
    }

    private boolean enoughResources(Town town, Army army) {
        List<TownResources> resources = town.getTownResources();
        for (TownResources resource : resources) {
            switch (army.getId()) {
                case 1:
                    if (resource.getValue() < 10) {
                        return false;
                    }
                    break;
                case 2:
                    if (resource.getValue() < 20) {
                        return false;
                    }
                    break;
                case 3:
                    if (resource.getValue() < 30) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
