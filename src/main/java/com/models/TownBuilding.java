package com.models;

import javax.persistence.*;



@Entity
@Table(name = "Town_Building")
@AssociationOverrides({
        @AssociationOverride(name = "pk.town",
                joinColumns = @JoinColumn(name = "TOWN_ID")),
        @AssociationOverride(name = "pk.building",
                joinColumns = @JoinColumn(name = "BUILDING_ID"))
})
public class TownBuilding {

    private TownBuildingId pk = new TownBuildingId();
    private int level;

    public TownBuilding() {
        this.level = 1;
    }

    @EmbeddedId
    public TownBuildingId getPk() {
        return pk;
    }

    public void setPk(TownBuildingId pk) {
        this.pk = pk;
    }

    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel(){
        this.level++;
    }

    @Transient
    public Town getTown() {
        return getPk().getTown();
    }

    public void setTown(Town town) {
        getPk().setTown(town);
    }

    @Transient
    public Building getBuilding() {
        return getPk().getBuilding();
    }

    public void setBuilding(Building building) {
        getPk().setBuilding(building);
    }
}
