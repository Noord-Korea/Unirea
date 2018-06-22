package com.restserver.handler;

import com.dbal.repository.*;
import com.models.*;
import com.restserver.buildings.resource.ResourceType;
import com.restserver.buildings.resource.models.*;
import com.restserver.json.request.building.LevelUp;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingHandler implements IBuildingHandler {

    private TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
    private BuildingRepository buildingRepository = new BuildingRepository();
    private TownRepository townRepository = new TownRepository();
    private ResourceRepository resourceRepository = new ResourceRepository();
    private TownResourceRepository townResourceRepository = new TownResourceRepository();
    private BuildingQueueRepository buildingQueueRepository = new BuildingQueueRepository();

    public Building getNormalTownBuildings(int townId, int buildingId) {
        Town town = townRepository.findOne(townId);
        if (town == null) {
            return null;
        }
        List<TownBuilding> townBuildings = town.getTownBuildings();

        switch (buildingId) {
            //HEADQUARTERS
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

    public Reply levelUp(LevelUp levelUp) {
        Town town = townRepository.findOne(levelUp.getTownId());
        if (town == null){
            return new Reply(Status.NOTFOUND, "Town could not be found");
        }
        List<BuildingQueue> queues = town.getBuildingQueues();
        BuildingQueue queue = new BuildingQueue();
        TownBuildingId buildingPK = null;
        for (TownBuilding building : town.getTownBuildings()){
            if (building.getBuilding().getId() == levelUp.getBuildingId()){
                buildingPK = building.getPk();
            }
        }
        queue.setPk(buildingPK);
        int time;
        BaseNormalBuilding headquarters = (BaseNormalBuilding) getNormalTownBuildings(levelUp.getTownId(), 4);
        if (levelUp.getBuildingId() > 3){
            BaseNormalBuilding building = (BaseNormalBuilding) getNormalTownBuildings(levelUp.getTownId(), levelUp.getBuildingId());
            if (checkResourceRequirements(town.getTownResources(), building.getBuildingLevel(), levelUp.getBuildingId()) == null){
                return new Reply(Status.CONFLICT, "Not enough resources to upgrade");
            }
            townResourceRepository.save(checkResourceRequirements(town.getTownResources(), building.getBuildingLevel(), levelUp.getBuildingId()));
            time = 60 + (15*building.getBuildingLevel());
            time = time * (1 - (headquarters.getBuildingLevel() / 10));
        } else {
            BaseResourceBuilding building = (BaseResourceBuilding) getResourceBuilding(levelUp.getTownId(), levelUp.getBuildingId());
            if (checkResourceRequirements(town.getTownResources(), building.getBuildingLevel(), levelUp.getBuildingId()) == null){
                return new Reply(Status.CONFLICT, "Not enough resources to upgrade");
            }
            townResourceRepository.save(checkResourceRequirements(town.getTownResources(), building.getBuildingLevel(), levelUp.getBuildingId()));
            time = 60 + (15*building.getBuildingLevel());
            time = time * (1 - (headquarters.getBuildingLevel() / 10));
        }
        queue.setValue(time);
        queue.setDate(new Date());
        queues.add(queue);
        buildingQueueRepository.save(queues);
        return new Reply(Status.OK , "Building will start upgrading");

    }

    public Building getResourceBuilding(int townId, int buildingId) {

        Town town = townRepository.findOne(townId);
        if (town == null) {
            return null;
        }
        List<TownBuilding> townBuildings = town.getTownBuildings();

        switch (buildingId) {
            //OIL
            case 1:
                OilBuilding oilBuilding = new OilBuilding();
                return constructResourceBuilding(townBuildings, oilBuilding, buildingId, ResourceType.OIL.getCode());
            //IRON
            case 2:
                IronBuilding ironBuilding = new IronBuilding();
                return constructResourceBuilding(townBuildings, ironBuilding, buildingId, ResourceType.IRON.getCode());
            //WOOD
            case 3:
                WoodBuilding woodBuilding = new WoodBuilding();
                return constructResourceBuilding(townBuildings, woodBuilding, buildingId, ResourceType.WOOD.getCode());
            default:
                return null;
        }
    }

    private Building constructResourceBuilding(List<TownBuilding> townBuildings, BaseResourceBuilding building, int buildingId, int resourceType) {
        for (TownBuilding townBuilding : townBuildings) {
            if (townBuilding.getBuilding().getId() == buildingId) {
                building.setName(townBuilding.getBuilding().getName());
                building.setBuildingLevel(townBuilding.getLevel());
                building.setResourceProduction(calculateResourceProduction(townBuilding.getLevel()));
                building.setResourceType(resourceType);
            }
        }
        return building;
    }

    private Building constructNormalTownBuilding(List<TownBuilding> townBuildings, BaseNormalBuilding building, int buildingId) {
        for (TownBuilding townBuilding : townBuildings) {
            if (townBuilding.getBuilding().getId() == buildingId) {
                building.setName(townBuilding.getBuilding().getName());
                building.setBuildingLevel(townBuilding.getLevel());
            }
        }
        return building;
    }

    private double calculateResourceProduction(int buildingLevel) {
        double pow = Math.pow(buildingLevel, 1.5);
        return 30 * pow;
    }

    private List<TownResources> checkResourceRequirements(List<TownResources> resources, int buildingLevel, int buildingId){
        int woodAvailable = 0;
        int ironAvailable = 0;
        int oilAvailable = 0;
        for (TownResources resource : resources){
            switch (resource.getResource().getName()){
                case "Iron Mine":
                    ironAvailable = resource.getValue();
                    break;
                case "Oil Refinery":
                    oilAvailable = resource.getValue();
                    break;
                case "Forestry":
                    woodAvailable = resource.getValue();
                    break;
            }
        }
        Map<String, Integer> requirements = getResourceRequirements(buildingLevel, buildingId);
        if (requirements.get("Oil") > oilAvailable || requirements.get("Iron") > ironAvailable || requirements.get("Wood") > woodAvailable) {
            return null;
        } else{
            oilAvailable = oilAvailable - requirements.get("Oil");
            ironAvailable = ironAvailable - requirements.get("Iron");
            woodAvailable = woodAvailable - requirements.get("wood");
            for (TownResources resource : resources){
                switch (resource.getResource().getName()){
                    case "Iron Mine":
                        resource.setValue(ironAvailable);
                        break;
                    case "Oil Refinery":
                        resource.setValue(oilAvailable);
                        break;
                    case "Forestry":
                        resource.setValue(woodAvailable);
                        break;
                }
            }
            return resources;
        }
    }

    private Map<String, Integer> getResourceRequirements(int buildingLevel, int buildingId){
        Map<String, Integer> requirements = new HashMap<>();
        double pow = 0;
        switch(buildingId){
            //OIL
            case 1:
                pow = (int) Math.pow(buildingLevel, 1.25);
                requirements.put("Oil", (int) (50 * pow));
                requirements.put("Iron", (int) (35 * pow));
                requirements.put("Wood", (int) (40 * pow));
                return requirements;
            //IRON
            case 2:
                pow = Math.pow(buildingLevel, 1.25);
                requirements.put("Oil", (int) (60 * pow));
                requirements.put("Iron", (int) (45 * pow));
                requirements.put("Wood", (int) (50 * pow));
                return requirements;
            //WOOD
            case 3:
                pow = Math.pow(buildingLevel, 1.25);
                requirements.put("Oil", (int) (55 * pow));
                requirements.put("Iron", (int) (40 * pow));
                requirements.put("Wood", (int) (45 * pow));
                return requirements;
            //HEADQUARTERS
            case 4:
                pow = Math.pow(buildingLevel, 1.25);
                requirements.put("Oil", (int) (58 * pow));
                requirements.put("Iron", (int) (45 * pow));
                requirements.put("Wood", (int) (47 * pow));
                return requirements;
            //WAREHOUSE
            case 5:
                pow = Math.pow(buildingLevel, 1.3);
                requirements.put("Oil", (int) (40 * pow));
                requirements.put("Iron", (int) (42 * pow));
                requirements.put("Wood", (int) (48 * pow));
                return requirements;
            //BARRACKS
            case 6:
                pow = Math.pow(buildingLevel, 1.4);
                requirements.put("Oil", (int) (50 * pow));
                requirements.put("Iron", (int) (52 * pow));
                requirements.put("Wood", (int) (47 * pow));
                return requirements;
            //TRAINING GROUNDS
            case 7:
                pow = Math.pow(buildingLevel, 1.35);
                requirements.put("Oil", (int) (43 * pow));
                requirements.put("Iron", (int) (46 * pow));
                requirements.put("Wood", (int) (49 * pow));
                return requirements;
            //FOUNDRY
            case 8:
                pow = Math.pow(buildingLevel, 1.4);
                requirements.put("Oil", (int) (48 * pow));
                requirements.put("Iron", (int) (58 * pow));
                requirements.put("Wood", (int) (55 * pow));
                return requirements;
            //AMMUNITION DEPOT
            case 9:
                pow = Math.pow(buildingLevel, 1.46);
                requirements.put("Oil", (int) (80 * pow));
                requirements.put("Iron", (int) (68 * pow));
                requirements.put("Wood", (int) (74 * pow));
                return requirements;
            //WALL
            case 10:
                pow = Math.pow(buildingLevel, 1.56);
                requirements.put("Oil", (int) (64 * pow));
                requirements.put("Iron", (int) (59 * pow));
                requirements.put("Wood", (int) (68 * pow));
                return requirements;
        }
        return null;
    }
}