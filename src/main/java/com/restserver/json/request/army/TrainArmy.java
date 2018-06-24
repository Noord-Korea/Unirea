package com.restserver.json.request.army;

public class TrainArmy {
    private int armyId;
    private int value;
    private int townId;
    private String token;

    public TrainArmy(int army, int value, int townId, String token) {
        this.armyId = army;
        this.value = value;
        this.townId = townId;
        this.token = token;
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

    public int getArmy() {
        return armyId;
    }

    public void setArmy(int army) {
        this.armyId = army;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
