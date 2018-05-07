package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.IAccountHandler;
import com.restserver.json.result.account.ChangePassword;
import com.restserver.json.result.account.Login;
import com.restserver.json.result.account.Register;

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
        String output = "Geslaagd Email: " + login.getEmail() + " Password: " + login.getPassword();

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
        String output = "Geslaagd Email: " + register.getEmail() + " Username: " + register.getUsername() + " Password: " + register.getPassword();

        return Response.status(200).entity(output).build();
    }

    @POST @Consumes("application/json")
    @Path("/changepassword")
    public Response getChangePassword(String data) {
        System.out.println(data);
        Gson gson = new Gson();
        ChangePassword changePassword = gson.fromJson(data, ChangePassword.class);
        System.out.println(changePassword.getEmail());
        System.out.println(changePassword.getUsername());
        System.out.println(changePassword.getNewPassword());
        System.out.println(changePassword.getVerifyPassword());
        String output = "Geslaagd Email: " + changePassword.getEmail() + " Username: " + changePassword.getUsername() + " New Password: " + changePassword.getNewPassword() + " Verify Password: " + changePassword.getVerifyPassword();

        return Response.status(200).entity(output).build();
    }
}
