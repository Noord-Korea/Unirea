package com.restserver.json.request;

import java.util.HashMap;
import java.util.Map;

public class MoveArmy {
    private Map<Integer, Integer> armyIds;
    private int targetTownId;
    private int value;
    private int townId;
    private String token;

    public MoveArmy(HashMap<Integer, Integer> armyIds, int targetTownId, int value, int townId, String token) {
        this.armyIds = armyIds;
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

    public Map<Integer, Integer> getArmyIds() {
        return armyIds;
    }

    public void setArmyIds(Map<Integer, Integer> armyIds) {
        this.armyIds = armyIds;
    }
}
