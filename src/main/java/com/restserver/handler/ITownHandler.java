package com.restserver.handler;

import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.response.Reply;

public interface ITownHandler {
    Reply getTown();
    Reply createTown(BaseTownRequest baseTownRequest);
}
