package com.restserver.json.response.town;

import java.util.List;

public class MapResponse {
    private List<TownPosition> maps;

    public MapResponse(List<TownPosition> maps) {
        this.maps = maps;
    }

    public List<TownPosition> getMaps() {
        return maps;
    }
}
