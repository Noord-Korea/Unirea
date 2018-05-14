package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.IAccountHandler;
import com.restserver.json.request.account.ChangePassword;
import com.restserver.json.request.account.Login;
import com.restserver.json.request.account.Register;
import com.restserver.json.response.Reply;

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
    public Response login(String data) {
        Gson gson = new Gson();
        Login login = gson.fromJson(data, Login.class);
        String output = "Geslaagd Email: " + login.getEmail() + " Password: " + login.getPassword();

        Reply reply = handler.login(login);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST @Consumes("application/json")
    @Path("/register")
    public Response register(String data) {
        System.out.println(data);
        Gson gson = new Gson();
        Register register = gson.fromJson(data, Register.class);
        String output = "Geslaagd Email: " + register.getEmail() + " Password: " + register.getPassword();
        System.out.println(output);
        Reply reply = handler.register(register);

        System.out.println(reply.getStatus());
        System.out.println(reply.getMessage());
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST @Consumes("application/json")
    @Path("/changepassword")
    public Response changePassword(String data) {
        Gson gson = new Gson();
        ChangePassword changePassword = gson.fromJson(data, ChangePassword.class);
        String output = "Geslaagd Email: " + changePassword.getEmail() + " Username: " + changePassword.getUsername() + " New Password: " + changePassword.getNewPassword() + " Verify Password: " + changePassword.getVerifyPassword();

        handler.changePassword(changePassword);

        return Response.status(200).entity(output).build();
    }
}
