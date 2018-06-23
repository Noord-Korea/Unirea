package com.models;

import java.util.Date;

public class ArmyMovementQueue {
    private TownArmyId pk = new TownArmyId();
    private int value;
    private Date date;
    private int homeTownId;
    private int targetTownId;
    private boolean goingHome;

    public TownArmyId getPk() {
        return pk;
    }

    public void setPk(TownArmyId pk) {
        this.pk = pk;
    }

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
