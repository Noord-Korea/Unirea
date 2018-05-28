package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.IAccountHandler;
import com.restserver.handler.ITownHandler;
import com.restserver.json.request.account.Login;
import com.restserver.json.request.town.Town;
import com.restserver.json.response.Reply;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/town")
public class TownService {

    private static ITownHandler handler;

    public static void setHandler(ITownHandler townHandler){
        handler = townHandler;
    }

    @GET @Consumes("application/json")
    @Path("/gettown")
    public Response GetTown(String data) {
        Gson gson = new Gson();
        Town town = gson.fromJson(data, Town.class);
        Reply reply = handler.getTown();

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
}
