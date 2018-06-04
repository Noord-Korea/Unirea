package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.ITownHandler;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.request.town.TownId;
import com.restserver.json.response.town.Town;
import com.restserver.json.response.Reply;

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
        Gson gson = new Gson();
        BaseTownRequest town = gson.fromJson(data, BaseTownRequest.class);
        Reply reply = handler.getTown();

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
}
