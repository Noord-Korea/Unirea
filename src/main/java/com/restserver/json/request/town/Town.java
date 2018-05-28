package com.restserver.json.request.town;

import com.models.BuildingQueue;
import com.models.TownArmy;
import com.models.TownBuildings;
import com.models.TownResources;

import java.util.HashSet;
import java.util.Set;

public class Town {
    private Set<TownResources> townResources = new HashSet<TownResources>(0);
    private Set<TownBuildings> townBuildings = new HashSet<>(0);

    private Set<BuildingQueue> buildingQueues = new HashSet<>(0);

    private Set<TownArmy> townArmies = new HashSet<>(0);

    public Town(Set<TownResources> townResources, Set<TownBuildings> townBuildings, Set<BuildingQueue> buildingQueues, Set<TownArmy> townArmies) {
        this.townResources = townResources;
        this.townBuildings = townBuildings;
        this.buildingQueues = buildingQueues;
        this.townArmies = townArmies;
    }

    public Set<TownResources> getTownResources() {
        return townResources;
    }

    public Set<TownBuildings> getTownBuildings() {
        return townBuildings;
    }

    public Set<BuildingQueue> getBuildingQueues() {
        return buildingQueues;
    }

    public Set<TownArmy> getTownArmies() {
        return townArmies;
    }
}
