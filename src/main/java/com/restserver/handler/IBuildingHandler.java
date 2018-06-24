package com.restserver.handler;

import com.models.Building;

import java.util.Map;

public interface IBuildingHandler {
    Building getResourceBuilding(int townId, int buildingId);
    Building getNormalTownBuildings(int townId, int buildingId);
    Map<String, Integer> getResourceRequirements(int buildingLevel, int buildingId);
    double calculateResourceProduction(int buildingLevel);
}
