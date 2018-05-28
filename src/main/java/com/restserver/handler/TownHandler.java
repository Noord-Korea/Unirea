package com.restserver.handler;


import com.dbal.repository.IRepository;
import com.models.AccessToken;
import com.models.Player;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

public class TownHandler implements ITownHandler {
    private IRepository townRepository;

    public TownHandler(IRepository townRepository) {
        this.townRepository = townRepository;
    }

    public Reply getTown(){
        return new Reply(Status.NotFound, "Not yet implemented");
    }

    @Override
    public Reply createTown(BaseTownRequest baseTownRequest) {
        AccessToken accessToken = baseTownRequest.getToken();
        return null;
    }
}
