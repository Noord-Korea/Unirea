package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.AccountHandler;
import com.restserver.handler.IAccountHandler;
import com.restserver.json.result.Login;
import com.restserver.json.result.Register;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountService {

    private static IAccountHandler handler;

    public static void setHandler(IAccountHandler accountHandler){
        handler = accountHandler;
    }

    @POST @Consumes("application/json")
    @Path("/login")
    public Response getLogin(String data) {
        System.out.println(data);
        Gson gson = new Gson();
        Login login = gson.fromJson(data, Login.class);
        System.out.println(login.getEmail());
        System.out.println(login.getPassword());
        String output = "Geslaagd";

        handler.Login(login);

        return Response.status(200).entity(output).build();
    }

    @POST @Consumes("application/json")
    @Path("/register")
    public Response getRegister(String data) {
        System.out.println(data);
        Gson gson = new Gson();
        Register register = gson.fromJson(data, Register.class);
        System.out.println(register.getEmail());
        System.out.println(register.getPassword());
        System.out.println(register.getUsername());
        String output = "Geslaagd";

        return Response.status(200).entity(output).build();
    }
}
