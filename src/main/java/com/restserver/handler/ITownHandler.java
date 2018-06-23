package com.restserver.handler;

import com.models.Player;
import com.restserver.json.response.Reply;

public interface ITownHandler {
    Reply getTown(int townId);

    Reply getTownsByPlayer(Player player);

    Reply getTownsByPlayerId(int playerId);

    Reply createTown(Player player);

    Reply getTownByAccesstoken(String token);
}
