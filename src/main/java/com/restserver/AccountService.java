package com.restserver;

import com.google.gson.Gson;
import com.restserver.Shared.ScoreResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountService {
    @GET
    @Path("/login/{value}")
    public Response getLogin(@PathParam("value") String msg) {
        ScoreResponse response = new ScoreResponse();
        response.setOperation("login");
        response.setExpression(msg);
        Gson gson = new Gson();
        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }
    @GET
    @Path("/register/{value}")
    public Response getRegister(@PathParam("value") String msg) {
        ScoreResponse response = new ScoreResponse();
        response.setOperation("register");
        response.setExpression(msg);
        try {
            int value = Integer.parseInt(msg);
            int result = (value<0)? -value:value;
            response.setResult(Integer.toString(result) + " Ez clap");
        } catch (NumberFormatException nfe) {
            response.setResult("invalid value");
        }
        Gson gson = new Gson();
        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }
}
