package com.restserver.factory;

import com.dbal.repository.BuildingRepository;
import com.dbal.repository.TownBuildingRepository;
import com.dbal.repository.TownRepository;
import com.models.Building;
import com.models.Player;
import com.models.Town;
import com.models.TownBuilding;
import com.restserver.buildings.resource.factory.IResourceBuildingFactory;
import com.restserver.buildings.resource.factory.ResourceBuildingFactory;
import com.restserver.exception.PlayerHasTownException;

import java.util.ArrayList;

public class TownFactory implements ITownFactory {

    private static TownRepository townRepository = new TownRepository();
    private static TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
    private static BuildingRepository buildingRepository = new BuildingRepository();
    private static IResourceBuildingFactory resourceBuildingFactory = new ResourceBuildingFactory();

    private TownFactory() {

    }

    public static Town createTown(Player player) throws PlayerHasTownException {
        if (player == null) {
            throw new IllegalArgumentException("Player is null");
        }
        if (!player.getTowns().isEmpty()) {
            throw new PlayerHasTownException();
        }

        Town town = new Town(player, "My first town");
        townRepository.save(town);

        ArrayList<Building> buildings = new ArrayList<>();
        //Oilbuilding
        buildings.add(buildingRepository.findOne(1));
        //Ironbuilding
        buildings.add(buildingRepository.findOne(2));
        //Woodbuilding
        buildings.add(buildingRepository.findOne(3));

        for (Building building : buildings) {
            TownBuilding townBuilding = new TownBuilding();
            townBuilding.setBuilding(building);
            townBuilding.setTown(town);
            townBuilding.setLevel(1);

            townBuildingRepository.save(townBuilding);
        }


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
