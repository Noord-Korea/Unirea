package com.models;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Building")

public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BUILDING_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
