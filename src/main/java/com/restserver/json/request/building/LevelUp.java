package com.restserver.json.request.building;

import com.models.AccessToken;

public class LevelUp {
    private AccessToken token;
    private int townId;
    private int buildingId;

    public LevelUp(AccessToken token, int townId, int buildingId) {
        this.token = token;
        this.townId = townId;
        this.buildingId = buildingId;
    }

    public AccessToken getToken() {
        return token;
    }

    public void setToken(AccessToken token) {
        this.token = token;
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
}
