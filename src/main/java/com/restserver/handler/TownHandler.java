package com.restserver.handler;

import com.dbal.repository.IRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.repository.TownRepository;
import com.google.gson.Gson;
import com.models.Player;
import com.models.Town;
import com.restserver.exception.PlayerHasTownException;
import com.restserver.factory.TownFactory;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.json.response.town.TownResponse;

import java.util.HashSet;
import java.util.Set;

public class TownHandler implements ITownHandler {
    private TownRepository townRepository;
    private PlayerRepository playerRepository = new PlayerRepository();
    private Gson gson = new Gson();

    public TownHandler(IRepository townRepository) {
        this.townRepository = (TownRepository) townRepository;
    }

    @Override
    public Reply getTown(int townId) {
        Town town = townRepository.findOne(townId);
        if (town == null) {
            return new Reply(Status.NOTFOUND, "No town found");
        } else {
            TownResponse townResponse = new TownResponse(town.getTownResources(), town.getTownBuildings(), town.getX(), town.getY());
            return new Reply(Status.OK, gson.toJson(townResponse));
        }
    }

    @Override
    public Reply getTownsByPlayer(Player player) {
        Set<Town> towns = player.getTowns();
        if (towns.isEmpty()) {
            return new Reply(Status.NOTFOUND, "No town found");
        } else {
            Set<TownResponse> townResponseSet = new HashSet();
            for (Town town : towns) {
                townResponseSet.add(new TownResponse(town.getTownResources(), town.getTownBuildings(), town.getX(), town.getY()));
            }
            return new Reply(Status.OK, gson.toJson(townResponseSet));
        }

    }

    @Override
    public Reply getTownsByPlayerId(int playerId) {
        Player player = playerRepository.findOne(playerId);
        return getTownsByPlayer(player);
    }

    @Override
    public Reply createTown(Player player) {
        if (player == null) {
            return null;
        }

        try {
            Town town = TownFactory.createTown(player);
            return getTown(town.getId());
        } catch (PlayerHasTownException e) {
            return new Reply(Status.ERROR, e.getMessage());
        }

    }
}