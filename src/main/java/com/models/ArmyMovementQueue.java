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
    private TownArmyId infantryPk;
    private TownArmyId cavalryPk;
    private TownArmyId armoredPk;
    private int value;
    private Date date;
    private int homeTownId;
    private int targetTownId;
    private boolean goingHome;

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

    public int getTargetTownId() {
        return targetTownId;
    }

    public void setTargetTownId(int targetTownId) {
        this.targetTownId = targetTownId;
    }

    public int getHomeTownId() {
        return homeTownId;
    }

    @EmbeddedId
    public TownArmyId getInfantryPk() {
        return infantryPk;
    }

    public void setInfantryPk(TownArmyId pk) {
        this.infantryPk = pk;
    }

    @EmbeddedId
    public TownArmyId getCavalryPk() {
        return cavalryPk;
    }

    public void setCavalryPk(TownArmyId cavalryPk) {
        this.cavalryPk = cavalryPk;
    }

    @EmbeddedId
    public TownArmyId getArmoredPk() {
        return armoredPk;
    }

    public void setArmoredPk(TownArmyId armoredPk) {
        this.armoredPk = armoredPk;
    }


    public void setHomeTownId(int homeTownId) {
        this.homeTownId = homeTownId;
    }

    @Transient
    public Town getTown() {
        return infantryPk.getTown();
    }

    public void setTown(Town town) {
        infantryPk.setTown(town);
    }

    @Transient
    public Army getArmy() {
        return infantryPk.getArmy();
    }

    public void setArmy(Army army) {
        infantryPk.setArmy(army);
    }

    public boolean isGoingHome() {
        return goingHome;
    }

    public void setGoingHome(boolean goingHome) {
        this.goingHome = goingHome;
    }
}
