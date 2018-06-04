package com.restserver.json.request.town;

public class TownId extends BaseTownRequest {

    private int townId;

    public TownId(String token, int townId) {
        super(token);
        this.townId = townId;
    }

    public int getTownId() {
        return townId;
    }
}
