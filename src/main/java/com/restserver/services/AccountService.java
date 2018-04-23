package com.restserver.services;

import com.google.gson.Gson;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountService {
    @POST
    @Path("/login/{value}")
    public Response getLogin(@PathParam("value") String msg) {
        ScoreResponse response = new ScoreResponse();
        response.setOperation("login");
        response.setExpression(msg);
        Gson gson = new Gson();
        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }
    @POST
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
