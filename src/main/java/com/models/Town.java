package com.models;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Town")

public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOWN_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.town")
    private Set<TownResources> townResources = new HashSet<TownResources>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.town")
    private Set<TownBuildings> townBuildings = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.town")
    private Set<BuildingQueue> buildingQueues = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.town")
    private Set<TownArmy> townArmies = new HashSet<>(0);

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

    public Set<TownBuildings> getTownBuildings() {
        return townBuildings;
    }

    public void setTownBuildings(Set<TownBuildings> townBuildings) {
        this.townBuildings = townBuildings;
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
}
