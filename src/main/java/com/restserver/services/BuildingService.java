package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.BuildingHandler;
import com.restserver.json.request.building.LevelUp;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/building")
public class BuildingService {
    private static BuildingHandler handler;

    public static void setHandler(BuildingHandler buildingHandler) {
        handler = buildingHandler;
    }

    @POST
    @Consumes("application/json")
    @Path("/levelup")
    public Response levelUp(String data) {
        Reply reply = null;
        Gson gson = new Gson();
        LevelUp levelUp = gson.fromJson(data, LevelUp.class);
        if (!AccessTokenUtil.checkAccess(levelUp.getToken(), AccessTokenLevel.LOGGEDIN)) {
            reply = new Reply(Status.NOAUTH, "Accesstoken not valid");
        }
        if (reply == null) {
            reply = handler.levelUp(levelUp);
        }

        if (reply == null) {
            reply = new Reply(Status.ERROR, "Something went wrong");
        }

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
}