package com.restserver.json.response.town;

import com.models.*;
import com.restserver.json.request.town.BaseTownRequest;

import java.util.HashSet;
import java.util.Set;

public class Town {
    private int x;
    private int y;

    private Set<TownResources> townResources = new HashSet<TownResources>(0);
    private Set<TownBuilding> townBuildings = new HashSet<>(0);

    private Set<BuildingQueue> buildingQueues = new HashSet<>(0);

    private Set<TownArmy> townArmies = new HashSet<>(0);

    public Town(Set<TownResources> townResources, Set<TownBuilding> townBuildings, Set<BuildingQueue> buildingQueues, Set<TownArmy> townArmies, int x, int y) {
        this.townResources = townResources;
        this.townBuildings = townBuildings;
        this.buildingQueues = buildingQueues;
        this.townArmies = townArmies;
        this.x = x;
        this.y = y;
    }

    public Set<TownResources> getTownResources() {
        return townResources;
    }

    public Set<TownBuilding> getTownBuildings() {
        return townBuildings;
    }

    public Set<BuildingQueue> getBuildingQueues() {
        return buildingQueues;
    }

    public Set<TownArmy> getTownArmies() {
        return townArmies;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
