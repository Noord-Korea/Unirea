package com.restserver.handler;


import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.IRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.specification.AccessTokenSpecification;
import com.dbal.specification.PlayerSpecification;
import com.dbal.specification.TownSpecification;
import com.google.gson.Gson;
import com.models.AccessToken;
import com.models.Player;
import com.models.Town;
import com.restserver.exception.PlayerHasTownException;
import com.restserver.factory.TownFactory;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.request.town.TownId;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.json.response.town.TownResponse;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

import java.util.HashSet;
import java.util.Set;

public class TownHandler implements ITownHandler {
    private IRepository townRepository;
    private AccessTokenRepository accessTokenRepository;
    private PlayerRepository playerRepository = new PlayerRepository();
    private Gson gson = new Gson();

    public TownHandler(IRepository townRepository, IRepository accessTokenRepository) {
        this.townRepository = townRepository;
        this.accessTokenRepository = (AccessTokenRepository) accessTokenRepository;
    }

    @Override
    public Reply getTown(int townId) {
        Town town = (Town) townRepository.findOne(townId);
        if (town == null) {
            return new Reply(Status.NOTFOUND, "No town found");
        } else {
            return new Reply(Status.OK, gson.toJson(town));
        }
    }

    @Override
    public Reply getTownsByPlayer(Player player) {
        Set<Town> towns = player.getTowns();
        if (towns.isEmpty()) {
            return new Reply(Status.NOTFOUND, "No town found");
        } else {
            Set<TownResponse> townResponseSet = new HashSet();
            for (Town town : towns){
                townResponseSet.add(new TownResponse(town.getTownResources(),town.getTownBuildings(),town.getX(),town.getY()));
            }
            return new Reply(Status.OK, gson.toJson(townResponseSet));
        }

    }

    @Override
    public Reply getTownsByPlayerId(int playerId) {
        Player player = (Player) playerRepository.findOne(playerId);
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