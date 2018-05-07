package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.json.result.Login;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountService {

    @POST @Consumes("application/json")
    @Path("/login")
    public Response getLogin(Login data) {
        System.out.println(data.getUsername());
        System.out.println(data.getPassword());

        Gson gson = new Gson();
        String output = gson.toJson(data);

        return Response.status(200).entity(output).build();
    }
}
