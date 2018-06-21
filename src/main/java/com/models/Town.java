package com.models;

import com.restserver.buildings.resource.ResourceType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "Town")

public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;
    @NotNull
    private int x;
    @NotNull
    private int y;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "pk.town")
    private Set<TownResources> townResources = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "pk.town")
    private Set<TownBuilding> townBuildings = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "pk.town")
    private Set<BuildingQueue> buildingQueues = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "pk.town")
    private Set<ArmyQueue> armyQueues = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "pk.town")
    private Set<TownArmy> townArmies = new HashSet<>(0);

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Set<TownArmy> getTownArmies() {
        return townArmies;
    }

    public void setTownArmies(Set<TownArmy> townArmies) {
        this.townArmies = townArmies;
    }

    public Set<TownResources> getTownResources() {
        return townResources;
    }

    public void setTownResources(Set<TownResources> townResources) {
        this.townResources = townResources;
    }

    public void addTownResource(TownResources townResources) {
        this.townResources.add(townResources);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Set<TownBuilding> getTownBuildings() {
        return townBuildings;
    }

    public void setTownBuildings(Set<TownBuilding> townBuildings) {
        this.townBuildings = townBuildings;
    }

    public void addTownBuilding(TownBuilding townBuilding) {
        townBuilding.setLevel(1);
        this.townBuildings.add(townBuilding);
    }

    public void addResource(Resource resource, int amount) {
        for (TownResources townResource : townResources) {
            if (townResource.getResource().getId() == resource.getId()) {
                townResource.addResource(amount);
            }
        }
    }

    public void addResource(ResourceType resourceType, int amount) {
        Resource resource = null;
        for (TownResources townResource : townResources) {
            if (townResource.getResource().getName().equalsIgnoreCase(String.valueOf(resourceType))) {
                addResource(townResource.getResource(), amount);
            }
        }
    }

    public Set<BuildingQueue> getBuildingQueues() {
        return buildingQueues;
    }

    public void setBuildingQueues(Set<BuildingQueue> buildingQueues) {
        this.buildingQueues = buildingQueues;
    }

    public String getName() {
        return name;
    }

    public Town() {
    }

    public Town(Player player, String name) {
        Random r = new Random();

        this.player = player;
        this.name = name;
        this.x = r.nextInt(50);
        this.y = r.nextInt(50);
    }

    public Set<ArmyQueue> getArmyQueues() {
        return armyQueues;
    }

    public void setArmyQueues(Set<ArmyQueue> armyQueues) {
        this.armyQueues = armyQueues;
    }
}
