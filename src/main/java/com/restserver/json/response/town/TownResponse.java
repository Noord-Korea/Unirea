package com.restserver.json.response.town;

import com.models.*;

import java.util.HashSet;
import java.util.Set;

public class TownResponse {
    private int x;
    private int y;

    private Set<TownResources> townResources = new HashSet<>(0);
    private Set<TownBuilding> townBuildings = new HashSet<>(0);
    private Player player;
    private String name;
    private Set<BuildingQueue> buildingQueues = new HashSet<>();
    private Set<TownArmy> townArmies = new HashSet<>();

    public TownResponse(Set<TownResources> townResources, Set<TownBuilding> townBuildings, int x, int y, Player player, String name, Set<BuildingQueue> buildingQueues, Set<TownArmy> townArmies) {
        this.townResources = townResources;
        this.townBuildings = townBuildings;
        this.x = x;
        this.y = y;
        this.player = player;
        this.name = name;
        this.buildingQueues = buildingQueues;
        this.townArmies = townArmies;
    }

    public Set<TownResources> getTownResources() {
        return townResources;
    }

    public Set<TownBuilding> getTownBuildings() {
        return townBuildings;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTownResources(Set<TownResources> townResources) {
        this.townResources = townResources;
    }

    public void setTownBuildings(Set<TownBuilding> townBuildings) {
        this.townBuildings = townBuildings;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BuildingQueue> getBuildingQueues() {
        return buildingQueues;
    }

    public void setBuildingQueues(Set<BuildingQueue> buildingQueues) {
        this.buildingQueues = buildingQueues;
    }

    public Set<TownArmy> getTownArmies() {
        return townArmies;
    }

    public void setTownArmies(Set<TownArmy> townArmies) {
        this.townArmies = townArmies;
    }
}
