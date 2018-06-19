package com.restserver.handler;

import com.dbal.repository.BuildingRepository;
import com.dbal.repository.TownBuildingRepository;
import com.dbal.repository.TownRepository;
import com.models.Building;
import com.models.Town;
import com.models.TownBuilding;
import com.restserver.buildings.resource.Buildings;
import com.restserver.buildings.resource.ResourceType;
import com.restserver.buildings.resource.models.*;

import java.util.Set;

public class BuildingHandler {

    private TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
    private BuildingRepository buildingRepository = new BuildingRepository();
    private TownRepository townRepository = new TownRepository();

    public Building getNormalTownBuildings(int townId, int buildingId){
        Town town = townRepository.findOne(townId);
        if (town == null){
            return null;
        }
        Set<TownBuilding> townBuildings = town.getTownBuildings();

        switch (buildingId){
            case 4:
                HeadquartersBuilding headquartersBuilding = new HeadquartersBuilding("Headquarters");
                return constructNormalTownBuilding(townBuildings, headquartersBuilding, buildingId);
            //WAREHOUSE
            case 5:
                WarehouseBuilding warehouseBuilding = new WarehouseBuilding("Warehouse");
                return constructNormalTownBuilding(townBuildings, warehouseBuilding, buildingId);
            //BARRACKS
            case 6:
                BarracksBuilding barracksBuilding = new BarracksBuilding("Barracks");
                return constructNormalTownBuilding(townBuildings, barracksBuilding, buildingId);
            //TRAINING GROUNDS
            case 7:
                TrainingGroundsBuilding trainingGroundsBuilding = new TrainingGroundsBuilding("Training Grounds");
                return constructNormalTownBuilding(townBuildings, trainingGroundsBuilding, buildingId);
            //FOUNDRY
            case 8:
                FoundryBuilding foundryBuilding = new FoundryBuilding("Foundry");
                return constructNormalTownBuilding(townBuildings, foundryBuilding, buildingId);
            //AMMUNITION DEPOT
            case 9:
                AmmunitionDepotBuilding ammunitionDepotBuilding = new AmmunitionDepotBuilding("Ammunition Depot");
                return constructNormalTownBuilding(townBuildings, ammunitionDepotBuilding, buildingId);
            //WALL
            case 10:
                WallBuilding wallBuilding = new WallBuilding("Wall");
                return constructNormalTownBuilding(townBuildings, wallBuilding, buildingId);
            default:
                return null;
        }
    }
    
    public Building getResourceBuilding(int townId, int buildingId){
        
        Town town = townRepository.findOne(townId);
        if (town == null){
            return null;
        }
        Set<TownBuilding> townBuildings = town.getTownBuildings();
        
        switch (buildingId){
            //OIL
            case 1:
                OilBuilding oilBuilding = new OilBuilding();
                return constructResourceBuilding(townBuildings, oilBuilding, buildingId, Buildings.OIL.getCode());
            //IRON
            case 2:
                IronBuilding ironBuilding = new IronBuilding();
                return constructResourceBuilding(townBuildings, ironBuilding, buildingId, Buildings.IRON.getCode());
            //WOOD
            case 3:
                WoodBuilding woodBuilding = new WoodBuilding();
                return constructResourceBuilding(townBuildings, woodBuilding, buildingId, Buildings.WOOD.getCode());
            default:
                return null;
        }
    }

    private Building constructResourceBuilding(Set<TownBuilding> townBuildings, BaseResourceBuilding building, int buildingId, int resourceType) {
        for (TownBuilding townBuilding : townBuildings){
            if(townBuilding.getBuilding().getId() == buildingId){
                building.setName(townBuilding.getBuilding().getName());
                building.setBuildingLevel(townBuilding.getLevel());
                building.setResourceProduction(calculateResourceProduction(townBuilding.getLevel()));
                building.setResourceType(resourceType);
            }
        }
        return building;
    }

    private Building constructNormalTownBuilding(Set<TownBuilding> townBuildings, BaseNormalBuilding building, int buildingId) {
        for (TownBuilding townBuilding : townBuildings){
            if(townBuilding.getBuilding().getId() == buildingId){
                building.setName(townBuilding.getBuilding().getName());
                building.setBuildingLevel(townBuilding.getLevel());
            }
        }
        return building;
    }

    private int calculateResourceProduction(int buildingLevel){
        int production = 30 * buildingLevel;
        return (int) Math.pow(production, 1.5);
    }


}
