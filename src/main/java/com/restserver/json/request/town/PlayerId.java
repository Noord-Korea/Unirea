package com.restserver.json.request.town;

public class PlayerId extends BaseTownRequest {

    private int playerId;

    public PlayerId(String token, int playerId) {
        super(token);
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
