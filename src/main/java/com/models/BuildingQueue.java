package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BuildingQueue")
@AssociationOverrides({
        @AssociationOverride(name = "pk.town",
                joinColumns = @JoinColumn(name = "TOWN_ID")),
        @AssociationOverride(name = "pk.building",
                joinColumns = @JoinColumn(name = "BUILDING_ID"))
})
public class BuildingQueue {

    private TownBuildingId pk = new TownBuildingId();
    private int value;
    private Date date;

    public BuildingQueue() {
    }

    @EmbeddedId
    public TownBuildingId getPk() {
        return pk;
    }

    public void setPk(TownBuildingId pk) {
        this.pk = pk;
    }

    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
