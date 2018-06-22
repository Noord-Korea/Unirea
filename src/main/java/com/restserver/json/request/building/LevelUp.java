package com.restserver.json.request.building;

public class LevelUp {
    private String token;
    private int townId;
    private int buildingId;

    public LevelUp(String token, int townId, int buildingId) {
        this.token = token;
        this.townId = townId;
        this.buildingId = buildingId;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
