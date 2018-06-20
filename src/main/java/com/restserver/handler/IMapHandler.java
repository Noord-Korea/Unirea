package com.restserver.handler;

import com.restserver.json.response.town.TownPosition;

import java.util.List;

public interface IMapHandler {
    List<TownPosition> getAllTowns();
}
