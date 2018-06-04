package com.restserver.handler;

import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.request.town.TownId;
import com.restserver.json.response.Reply;

public interface ITownHandler {
    Reply getTown(TownId data);
    Reply createTown(BaseTownRequest baseTownRequest);
}
