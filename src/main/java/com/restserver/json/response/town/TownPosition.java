package com.restserver.json.response.town;

public class TownPosition {
    int id;
    int x;
    int y;

    public TownPosition(int id, int x, int y) {
        this.id = id;
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
