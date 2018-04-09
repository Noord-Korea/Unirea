package com.models;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Town")

public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;
    private Set<TownResources> townResources = new HashSet<TownResources>(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.town")
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

    public String getName() {
        return name;
    }

    public Town() {
    }
}
