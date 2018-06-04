package com.restserver.handler;

import com.dbal.repository.TownRepository;
import com.models.Town;
import com.restserver.json.response.town.TownPosition;

import java.util.ArrayList;
import java.util.List;

public class MapHandler implements IMapHandler {
    @Override
    public List<TownPosition> getAllTowns() {
        ArrayList<TownPosition> map = new ArrayList<>();

        TownRepository townRepository = new TownRepository();
        List<Town> towns = townRepository.findAll();
        for (Town town : towns) {
            map.add(new TownPosition(town.getId(), town.getX(), town.getY()));
        }

        return map;
    }
}
