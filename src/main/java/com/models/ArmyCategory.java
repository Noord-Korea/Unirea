package com.models;


import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "ArmyCategory")
public class ArmyCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARMY_CATEGORY_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    private String name;
}
