package com.restserver.services;

import com.restserver.handler.IMapHandler;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.json.response.town.MapResponse;
import com.restserver.json.response.town.TownPosition;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/map")
public class MapService {

    private static IMapHandler handler;

    public static void setHandler(IMapHandler mapHandler){
        handler = mapHandler;
    }

    @GET @Consumes("application/json")
    @Path("/all")
    public Response getTowns() {
        List<TownPosition> towns = handler.getAllTowns();

        Reply reply = new Reply(Status.OK, new MapResponse(towns));

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
}
