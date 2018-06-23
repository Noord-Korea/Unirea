package com.restserver.json.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MoveArmy {
    private ArrayList<Integer> troopAmount;
    private int targetTownId;
    private int value;
    private int townId;
    private String token;

    public MoveArmy(ArrayList<Integer> troopAmount, int targetTownId, int value, int townId, String token) {
        this.troopAmount = troopAmount;
        this.targetTownId = targetTownId;
        this.value = value;
        this.townId = townId;
        this.token = token;
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

    public ArrayList<Integer> getTroopAmount() {
        return troopAmount;
    }

    public void setTroopAmount(ArrayList<Integer> troopAmount) {
        this.troopAmount = troopAmount;
    }
}
