package com.restserver.handler;

import com.models.Player;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.request.town.TownId;
import com.restserver.json.response.Reply;

public interface ITownHandler {
    Reply getTown(int townId);
    Reply createTown(Player player);
}
