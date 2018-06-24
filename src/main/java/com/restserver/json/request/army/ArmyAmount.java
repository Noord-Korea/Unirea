package com.restserver.json.request.army;

public class ArmyAmount {
    private int armyId;
    private int troopAmount;

    public int getTroopAmount() {
        return troopAmount;
    }

    public void setTroopAmount(int troopAmount) {
        this.troopAmount = troopAmount;
    }

    public int getArmyId() {
        return armyId;
    }

    public void setArmyId(int armyId) {
        this.armyId = armyId;
    }
}
