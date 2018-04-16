package com.restserver;

import com.google.gson.Gson;
import com.restserver.Shared.ScoreResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/abs")
public class ScoreService {
    @GET
    @Path("/{value}")
    public Response getMsg(@PathParam("value") String msg) {
        ScoreResponse response = new ScoreResponse();
        response.setOperation("abs");
        response.setExpression(msg);
        try {
            int value = Integer.parseInt(msg);
            int result = (value<0)? -value:value;
            response.setResult(Integer.toString(result));
        } catch (NumberFormatException nfe) {
            response.setResult("invalid value");
        }
        Gson gson = new Gson();
        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }
}
