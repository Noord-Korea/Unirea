package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ArmyMovement")
@AssociationOverrides({
        @AssociationOverride(name = "pk.town",
                joinColumns = @JoinColumn(name = "TOWN_ID")),
        @AssociationOverride(name = "pk.army",
                joinColumns = @JoinColumn(name = "ARMY_ID"))

})
public class ArmyMovementQueue {
    private TownArmyId pk = new TownArmyId();
    private int value;
    private Date date;
    private int homeTownId;
    private int targetTownId;
    private boolean goingHome;

    @EmbeddedId
    public TownArmyId getPk() {
        return pk;
    }

    public void setPk(TownArmyId pk) {
        this.pk = pk;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Transient
    public Town getTown() {
        return pk.getTown();
    }

    public void setTown(Town town) {
        pk.setTown(town);
    }

    @Transient
    public Army getArmy() {
        return getPk().getArmy();
    }

    public void setArmy(Army army) {
        pk.setArmy(army);
    }

    public int getTargetTownId() {
        return targetTownId;
    }

    public void setTargetTownId(int targetTownId) {
        this.targetTownId = targetTownId;
    }

    public int getHomeTownId() {
        return homeTownId;
    }

    public void setHomeTownId(int homeTownId) {
        this.homeTownId = homeTownId;
    }

    public boolean isGoingHome() {
        return goingHome;
    }

    public void setGoingHome(boolean goingHome) {
        this.goingHome = goingHome;
    }
}
