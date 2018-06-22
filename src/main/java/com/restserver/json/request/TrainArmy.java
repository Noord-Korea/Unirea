package com.restserver.json.request;

import com.models.AccessToken;
import com.models.Army;

public class TrainArmy {
    private Army army;
    private int value;
    private int townId;
    private AccessToken token;

    public TrainArmy(Army army, int value, int townId, AccessToken token) {
        this.army = army;
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

    public AccessToken getToken() {
        return token;
    }

    public void setToken(AccessToken token) {
        this.token = token;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
