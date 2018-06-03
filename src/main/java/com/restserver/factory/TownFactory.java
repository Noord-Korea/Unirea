package com.restserver.factory;

import com.dbal.repository.TownRepository;
import com.models.*;
import com.restserver.buildings.resource.factory.IResourceBuildingFactory;
import com.restserver.buildings.resource.factory.ResourceBuildingFactory;
import com.restserver.buildings.resource.models.IResourceBuilding;
import com.restserver.buildings.resource.models.IronBuilding;
import com.restserver.exception.PlayerHasTownException;

import java.util.HashSet;
import java.util.Set;

public class TownFactory implements ITownFactory {
    private static TownRepository townRepository = new TownRepository();
    private static IResourceBuildingFactory resourceBuildingFactory = new ResourceBuildingFactory();

    public static Town createTown(Player player) throws PlayerHasTownException {
        if(player == null){
            throw new IllegalArgumentException("Player is null");
        }
        if(player.getTowns().size() != 0){
            throw new PlayerHasTownException();
        }

        Town town = new Town(player, "My first town");

        Set<TownBuildings> townBuildings = new HashSet<>();
        TownBuildings ironBuilding = new TownBuildings();
        ironBuilding.setBuilding(resourceBuildingFactory.createIronBuilding());
        TownBuildings oilBuilding = new TownBuildings();
        ironBuilding.setBuilding(resourceBuildingFactory.createOilBuilding());
        TownBuildings woodBuilding = new TownBuildings();
        ironBuilding.setBuilding(resourceBuildingFactory.createWoodBuilding());
        townBuildings.add(ironBuilding);
        townBuildings.add(oilBuilding);
        townBuildings.add(woodBuilding);

        town.setTownBuildings(townBuildings);
        //TODO: add basic buildings
        //TODO: add resources

        townRepository.save(town);
        return town;
    }
}
