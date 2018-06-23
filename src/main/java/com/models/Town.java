package com.models;

import com.dbal.repository.*;
import com.dbal.specification.TownIdSpecification;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "Town")

public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;
    @NotNull
    private int x;
    @NotNull
    private int y;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<TownArmy> getTownArmies() {
        TownArmyRepository townArmyRepository = new TownArmyRepository();
        return townArmyRepository.findAll(TownIdSpecification.getByTownId(this.id));
    }

    public List<TownResources> getTownResources() {
        TownResourceRepository townResourceRepository = new TownResourceRepository();
        return townResourceRepository.findAll(TownIdSpecification.getByTownId(this.id));

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<TownBuilding> getTownBuildings() {
        TownBuildingRepository townBuildingRepository = new TownBuildingRepository();
        return townBuildingRepository.findAll(TownIdSpecification.getByTownId(id));
    }


    public List<BuildingQueue> getBuildingQueues() {
        BuildingQueueRepository buildingQueueRepository = new BuildingQueueRepository();
        return buildingQueueRepository.findAll(TownIdSpecification.getByTownId(id));
    }

    public List<ArmyMovementQueue> getArmyMovementQueues() {
        ArmyMovementQueueRepository armyMovementQueueRepository = new ArmyMovementQueueRepository();
        return armyMovementQueueRepository.findAll(TownIdSpecification.getByTownId(id));
    }



    public String getName() {
        return name;
    }

    public Town() {
    }

    public Town(Player player, String name) {
        Random r = new Random();

        this.player = player;
        this.name = name;
        this.x = r.nextInt(50);
        this.y = r.nextInt(50);
    }


}