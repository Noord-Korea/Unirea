package com.restserver.factory;

import com.dbal.repository.BuildingRepository;
import com.dbal.repository.TownBuildingIdRepository;
import com.dbal.repository.TownBuildingRepository;
import com.dbal.repository.TownRepository;
import com.models.*;
import com.restserver.buildings.resource.factory.IResourceBuildingFactory;
import com.restserver.buildings.resource.factory.ResourceBuildingFactory;
import com.restserver.exception.PlayerHasTownException;

public class TownFactory implements ITownFactory {

    private static TownRepository townRepository = new TownRepository();
    private static TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
    private static BuildingRepository buildingRepository = new BuildingRepository();
    private static TownBuildingIdRepository townBuildingIdRepository = new TownBuildingIdRepository();
    private static IResourceBuildingFactory resourceBuildingFactory = new ResourceBuildingFactory();

    private TownFactory() {

    }

    public static Town createTown(Player player) throws PlayerHasTownException {
        if(player == null){
            throw new IllegalArgumentException("Player is null");
        }
        if(!player.getTowns().isEmpty()){
            throw new PlayerHasTownException();
        }

        Town town = new Town(player, "My first town");
        townRepository.save(town);



        //region iron
        //endregion

        /*
        //region oil

        //endregion
        */
        //TODO: add basic buildings
        //TODO: add resources

        return town;
    }
}
