package com.restserver.factory;

import com.dbal.repository.BuildingRepository;
import com.dbal.repository.TownBuildingIdRepository;
import com.dbal.repository.TownBuildingRepository;
import com.dbal.repository.TownRepository;
import com.models.*;
import com.restserver.buildings.resource.factory.IResourceBuildingFactory;
import com.restserver.buildings.resource.factory.ResourceBuildingFactory;
import com.restserver.exception.PlayerHasTownException;

import java.util.HashSet;
import java.util.Set;

public class TownFactory implements ITownFactory {
    private static TownRepository townRepository = new TownRepository();
    private static TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
    private static BuildingRepository buildingRepository = new BuildingRepository();
    private static TownBuildingIdRepository townBuildingIdRepository = new TownBuildingIdRepository();
    private static IResourceBuildingFactory resourceBuildingFactory = new ResourceBuildingFactory();

    public static Town createTown(Player player) throws PlayerHasTownException {
        if(player == null){
            throw new IllegalArgumentException("Player is null");
        }
        if(player.getTowns().size() != 0){
            throw new PlayerHasTownException();
        }

        Town town = new Town(player, "My first town");

        //region iron
        TownBuilding ironBuilding = new TownBuilding();
        ironBuilding.setBuilding(resourceBuildingFactory.createIronBuilding());

        town.addTownBuilding(ironBuilding);
        ironBuilding.setTown(town);
        ironBuilding.setLevel(1);

        TownBuildingId townBuildingId = new TownBuildingId();
        townBuildingId.setTown(town);
        townBuildingId.setBuilding(ironBuilding.getBuilding());

        buildingRepository.save(ironBuilding.getBuilding());
        townBuildingIdRepository.save(townBuildingId);
        townBuildingRepository.save(ironBuilding);

        //endregion

        /*
        //region oil
        TownBuilding oilBuilding = new TownBuilding();
        oilBuilding.setBuilding(resourceBuildingFactory.createOilBuilding());
        town.addTownBuilding(oilBuilding);

        oilBuilding.setTown(town);
        townBuildingId = new TownBuildingId();
        townBuildingId.setTown(town);
        townBuildingId.setBuilding(oilBuilding.getBuilding());

        buildingRepository.save(oilBuilding.getBuilding());
        townBuildingIdRepository.save(townBuildingId);
        townBuildingRepository.save(oilBuilding);
        //endregion

        //region wood
        TownBuilding woodBuilding = new TownBuilding();
        woodBuilding.setBuilding(resourceBuildingFactory.createWoodBuilding());
        town.addTownBuilding(woodBuilding);

        woodBuilding.setTown(town);

        townBuildingId = new TownBuildingId();
        townBuildingId.setTown(town);
        townBuildingId.setBuilding(woodBuilding.getBuilding());

        buildingRepository.save(woodBuilding.getBuilding());
        townBuildingIdRepository.save(townBuildingId);
        townBuildingRepository.save(woodBuilding);
        //endregion
        */
        //TODO: add basic buildings
        //TODO: add resources

        townRepository.save(town);
        return town;
    }
}
