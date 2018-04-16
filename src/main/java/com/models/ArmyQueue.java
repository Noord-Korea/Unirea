package com.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Army_Queue")
@AssociationOverrides({
        @AssociationOverride(name = "pk.town",
                joinColumns = @JoinColumn(name = "TOWN_ID")),
        @AssociationOverride(name = "pk.army",
                joinColumns = @JoinColumn(name = "ARMY_ID"))
})

public class ArmyQueue {
    private TownArmyId pk = new TownArmyId();
    private int value;
    private Date date;

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @EmbeddedId
    public TownArmyId getPk() {
        return pk;
    }

    public void setPk(TownArmyId pk) {
        this.pk = pk;
    }

    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
}
