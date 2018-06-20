package com.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ArmyCategory")
public class ArmyCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARMY_CATEGORY_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    private String name;

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "armyCategory", fetch = FetchType.EAGER)
    private Set<Army> armies = new HashSet<>();

}
