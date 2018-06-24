package com.restserver.json.response.town;

public class TownPosition {
    private int id;
    private int x;
    private int y;
    private int playerId;

    public TownPosition(int id, int playerId, int x, int y) {
        this.id = id;
        this.playerId = playerId;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
