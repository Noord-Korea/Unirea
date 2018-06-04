package com.restserver.services;

import com.google.gson.Gson;
import com.models.Player;
import com.restserver.handler.ITownHandler;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.request.town.TownId;
import com.restserver.json.response.Status;
import com.restserver.json.response.town.Town;
import com.restserver.json.response.Reply;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/town")
public class TownService {

    private static ITownHandler handler;

    public static void setHandler(ITownHandler townHandler){
        handler = townHandler;
    }

    @POST @Consumes("application/json")
    @Path("/get")
    public Response getTown(String data) {
        Gson gson = new Gson();
        TownId town = gson.fromJson(data, TownId.class);
        Reply reply = handler.getTown(town);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST @Consumes("application/json")
    @Path("/create")
    public Response createTown(String data) {
        Reply reply = null;
        Gson gson = new Gson();
        BaseTownRequest baseTownRequest = gson.fromJson(data, BaseTownRequest.class);

        if(!AccessTokenUtil.checkAccess(baseTownRequest.getToken(), AccessTokenLevel.LOGGEDIN)){
            reply = new Reply(Status.NOAUTH, "Accesstoken not valid");
        }
        if(reply == null){
            Player player = AccessTokenUtil.getPlayerFromToken(baseTownRequest.getToken());
            reply = handler.createTown(player);
        }

        if(reply == null){
            reply = new Reply(Status.ERROR, "Something went wrong");
        }

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
}
