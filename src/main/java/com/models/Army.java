package com.models;


import javax.persistence.*;


@Entity
@Table(name = "Army")

public class Army {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARMY_ID", unique = true, nullable = false)
    private int id;

    private String name;

    public Army() {
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

