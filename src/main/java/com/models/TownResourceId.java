package com.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TownResourceId {
    private Town town;
    private Resource resource;

    @ManyToOne
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @ManyToOne
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
