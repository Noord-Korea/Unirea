package com.restserver.handler;

import com.dbal.repository.IRepository;
import com.dbal.specification.PlayerSpecification;
import com.models.Player;
import com.models.AccessToken;
import com.restserver.accesstoken.AccessTokenFactory;
import com.restserver.accesstoken.IAccessTokenFactory;
import com.restserver.json.request.account.*;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

public class AccountHandler implements IAccountHandler {
    private IRepository repository;
    private IAccessTokenFactory accessTokenFactory = new AccessTokenFactory();

    public AccountHandler(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply login(Login data) {
        Player player = (Player) repository.findOne(PlayerSpecification.getByEmail(data.getEmail()));
        if (player == null){
            return new Reply(Status.NotFound, "Player doesn't exist");
        } else if (!player.getEmail().equals(data.getEmail()) || !(player.checkPassword(data.getPassword()))){
            return new Reply(Status.NoAccess, "Your login credentials were incorrect");
        }
        return new Reply(Status.Ok,"Success");

    }

    @Override
    public Reply logout(Logout data) {
        return null;
    }

    @Override
    public Reply register(Register data) {
        Player player = (Player) repository.findOne(PlayerSpecification.getByEmail(data.getEmail()));
        if (player == null){
            try {
                player = new Player(data.getUsername(), data.getEmail(), data.getPassword());
            } catch (IllegalArgumentException e){
                return new Reply(Status.Error,e.getMessage());
            }
            repository.save(player);
            return new Reply(Status.Ok, "Succesfully registered");
        } else{
            return new Reply(Status.Conflict, "Email is already used");
        }
    }

    @Override
    public Reply changePassword(ChangePassword data) {
        return null;
    }

    @Override
    public Reply update(UpdateAccount data) {
        return null;
    }

    @Override
    public Reply holidayReplacement(HolidayReplacement data) {
        return null;
    }

    @Override
    public Reply delete(Delete data) {
        return null;
    }

    @Override
    public AccessToken generateAccessToken(Player player){
        return accessTokenFactory.newToken(player);
    }
}
