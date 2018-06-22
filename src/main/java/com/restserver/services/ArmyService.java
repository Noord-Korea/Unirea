package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.ArmyHandler;
import com.restserver.json.request.TrainArmy;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/town")
public class ArmyService {

    private static ArmyHandler handler;

    public static void setHandler(ArmyHandler armyHandler) {
        handler = armyHandler;
    }

    @POST
    @Consumes("application/json")
    @Path("/train")
    public Response trainArmy(String data) {
        Reply reply = null;
        Gson gson = new Gson();
        TrainArmy trainArmy = gson.fromJson(data, TrainArmy.class);
        if (!AccessTokenUtil.checkAccess(trainArmy.getToken(), AccessTokenLevel.LOGGEDIN)) {
            reply = new Reply(Status.NOAUTH, "Accesstoken not valid");
        }
        if (reply == null) {
            reply = handler.trainArmy(trainArmy);
        }
        if (reply == null) {
            reply = new Reply(Status.ERROR, "something went wrong");
        }
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
}
