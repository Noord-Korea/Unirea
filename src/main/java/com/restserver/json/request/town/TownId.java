package com.restserver.json.request.town;

public class TownId extends BaseTownRequest {

    private int id;

    public TownId(String token, int id) {
        super(token);
        this.id = id;
    }

    public int getTownId() {
        return id;
    }
}
