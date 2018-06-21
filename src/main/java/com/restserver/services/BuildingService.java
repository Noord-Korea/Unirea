package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.BuildingHandler;
import com.restserver.json.request.building.LevelUp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/building")
public class BuildingService {
    private BuildingHandler handler = new BuildingHandler();

    @POST
    @Consumes("application/json")
    @Path("/levelup")
    public void levelUp(String data) {
        Gson gson = new Gson();
        LevelUp levelUp = gson.fromJson(data, LevelUp.class);
        handler.levelUp(levelUp);
    }
}