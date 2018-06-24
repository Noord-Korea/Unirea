package com.models;


import javax.persistence.*;

@Entity
@Table(name = "Town_Army")
@AssociationOverrides({
        @AssociationOverride(name = "pk.town",
                joinColumns = @JoinColumn(name = "TOWN_ID")),
        @AssociationOverride(name = "pk.army",
                joinColumns = @JoinColumn(name = "ARMY_ID"))
})

public class TownArmy {

    private TownArmyId pk = new TownArmyId();
    private int value;
    private int inTown = value;

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

    @Column(name = "inTown")
    public int getInTown() {
        return inTown;
    }

    public void setInTown(int inTown) {
        this.inTown = inTown;
    }

}
