package com.restserver.json.request.town;

public class PlayerId extends BaseTownRequest {

    private int id;

    public PlayerId(String token, int id) {
        super(token);
        this.id = id;
    }

    public int getPlayerId() {
        return id;
    }
}
