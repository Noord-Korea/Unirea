package com.models;


import javax.validation.constraints.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "Army")

public class Army {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARMY_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    private String name;


    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "armycategory_id")
    private ArmyCategory armyCategory;

    public ArmyCategory getArmyCategory() {
        return armyCategory;
    }

    public void setArmyCategory(ArmyCategory armyCategory) {
        this.armyCategory = armyCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

