package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ArmyMovement")

public class ArmyMovementQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ArmyQueue_ID", unique = true, nullable = false)
    private int id;
    private int value;
    private Date date;
    private int homeTownId;
    private int targetTownId;
    private boolean goingHome;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTargetTownId() {
        return targetTownId;
    }

    public void setTargetTownId(int targetTownId) {
        this.targetTownId = targetTownId;
    }

    public int getHomeTownId() {
        return homeTownId;
    }

    public void setHomeTownId(int homeTownId) {
        this.homeTownId = homeTownId;
    }

    public boolean isGoingHome() {
        return goingHome;
    }

    public void setGoingHome(boolean goingHome) {
        this.goingHome = goingHome;
    }
}
