package com.restserver.services;

import com.google.gson.Gson;
import com.restserver.handler.IAccountHandler;
import com.restserver.json.request.account.*;
import com.restserver.json.response.Reply;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountService {

    private static IAccountHandler handler;

    public static void setHandler(IAccountHandler accountHandler) {
        handler = accountHandler;
    }

    @POST
    @Consumes("application/json")
    @Path("/login")
    public Response login(String data) {
        Gson gson = new Gson();
        Login login = gson.fromJson(data, Login.class);
        Reply reply = handler.login(login);


        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/register")
    public Response register(String data) {
        Gson gson = new Gson();
        Register register = gson.fromJson(data, Register.class);
        Reply reply = handler.register(register);


        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/logout")
    public Response Logout(String data) {
        Gson gson = new Gson();
        Logout logout = gson.fromJson(data, Logout.class);
        Reply reply = handler.logout(logout);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/changepassword")
    public Response changePassword(String data) {
        Gson gson = new Gson();
        ChangePassword changePassword = gson.fromJson(data, ChangePassword.class);
        Reply reply = handler.changePassword(changePassword);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/delete")
    public Response delete(String data) {
        Gson gson = new Gson();
        Delete delete = gson.fromJson(data, Delete.class);
        Reply reply = handler.delete(delete);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/updateaccount")
    public Response update(String data) {
        Gson gson = new Gson();
        UpdateAccount updateAccount = gson.fromJson(data, UpdateAccount.class);
        Reply reply = handler.update(updateAccount);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/getaccount")
    public Response getAccount(String data) {
        Gson gson = new Gson();
        BaseRequest getAccount = gson.fromJson(data, BaseRequest.class);
        Reply reply = handler.getAccount(getAccount);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/holidayreplacement")
    public Response holidayReplacement(String data) {
        Gson gson = new Gson();
        HolidayReplacement holidayReplacement = gson.fromJson(data, HolidayReplacement.class);
        Reply reply = handler.holidayReplacement(holidayReplacement);

        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }


}
