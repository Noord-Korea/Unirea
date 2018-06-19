package com.models;
import javax.persistence.*;

@Entity
@Table(name = "Town_Resource")
@AssociationOverrides({
        @AssociationOverride(name = "pk.town",
                joinColumns = @JoinColumn(name = "TOWN_ID")),
        @AssociationOverride(name = "pk.resource",
                joinColumns = @JoinColumn(name = "RESOURCE_ID"))
})
public class TownResources {

    private TownResourceId pk = new TownResourceId();
    private int value;

    @EmbeddedId
    public TownResourceId getPk() {
        return pk;
    }

    public void setPk(TownResourceId pk) {
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
        return getPk().getTown();
    }

    public void setTown(Town town) {
        getPk().setTown(town);
    }

    @Transient
    public Resource getResource() {
        return getPk().getResource();
    }

    public void setResource(Resource resource) {
        getPk().setResource(resource);
    }
}
