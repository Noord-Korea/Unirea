package com.restserver.json.request.army;

import java.util.ArrayList;

public class MoveArmy {
    private ArrayList<ArmyAmount> troopAmount;
    private int targetTownId;
    private int townId;
    private String token;

    public MoveArmy(ArrayList<ArmyAmount> troopAmount, int targetTownId, int townId, String token) {
        this.troopAmount = troopAmount;
        this.targetTownId = targetTownId;
        this.townId = townId;
        this.token = token;
    }

    public int getTargetTownId() {
        return targetTownId;
    }

    public void setTargetTownId(int targetTownId) {
        this.targetTownId = targetTownId;
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

    public ArrayList<ArmyAmount> getTroopAmount() {
        return troopAmount;
    }

    public void setTroopAmount(ArrayList<ArmyAmount> troopAmount) {
        this.troopAmount = troopAmount;
    }
}
