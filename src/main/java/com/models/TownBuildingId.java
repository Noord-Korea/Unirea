package com.models;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TownBuildingId implements Serializable {
    private transient Town town;
    private transient Building building;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "TOWN_ID")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}


