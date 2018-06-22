package com.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Army")

public class Army {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARMY_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    private String name;

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

