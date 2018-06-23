package com.restserver.json.request;

public class MoveArmy {
    private int armyId;
    private int targetTownId;
    private int value;
    private int townId;
    private String token;

    public MoveArmy(int armyId, int targetTownId, int value, int townId, String token) {
        this.armyId = armyId;
        this.targetTownId = targetTownId;
        this.value = value;
        this.townId = townId;
        this.token = token;
    }

    public int getArmyId() {
        return armyId;
    }

    public void setArmyId(int armyId) {
        this.armyId = armyId;
    }

    public int getTargetTownId() {
        return targetTownId;
    }

    public void setTargetTownId(int targetTownId) {
        this.targetTownId = targetTownId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
