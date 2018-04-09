package com.models;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Resource")

public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESOURCE_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    private String name;

    public Resource() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
