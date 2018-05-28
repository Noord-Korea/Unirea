package com.restserver.handler;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.IRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.specification.AccessTokenSpecification;
import com.dbal.specification.PlayerSpecification;
import com.google.gson.Gson;
import com.models.Player;
import com.models.AccessToken;
import com.restserver.accesstoken.AccessTokenFactory;
import com.restserver.accesstoken.IAccessTokenFactory;
import com.restserver.json.request.account.*;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

public class AccountHandler implements IAccountHandler {
    private IRepository repository;
    private AccessTokenRepository accessTokenRepository = new AccessTokenRepository();

    public AccountHandler(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply login(Login data) {
        Player player = (Player) repository.findOne(PlayerSpecification.getByEmail(data.getEmail()));
        if (player == null){
            return new Reply(Status.NotFound, "Player doesn't exist");
        } else if (!player.getEmail().equals(data.getEmail()) || !player.checkPassword(data.getPassword())){
            return new Reply(Status.NoAccess, "Your login credentials were incorrect");
        }
        AccessToken token = generateAccessToken(player);
        accessTokenRepository.save(token);
        return new Reply(Status.Ok,token.getAccessToken());

    }

    @Override
    public Reply logout(Logout data) {
        if (data.getToken() == null){
            return new Reply(Status.NoAuth, "Player already logged out");
        } else {
            AccessToken token = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(data.getToken().getAccessToken()));
            if (token == null){
                return new Reply (Status.NotFound, "Token doesn't exist");
            }
            accessTokenRepository.delete(token);
            return new Reply(Status.Ok, "Player successfully logged out");
        }
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
            return new Reply(Status.Ok, "Successfully registered");
        } else{
            return new Reply(Status.Conflict, "Email is already used");
        }
    }

    //Todo: Maybe make an player update function as deleting and creating a player is somewhat brutal
    @Override
    public Reply changePassword(ChangePassword data) {
        if (!(data.getNewPassword().equals(data.getVerifyPassword()))){
            return new Reply(Status.Conflict, "Passwords don't match");
        } else if (!AccessTokenUtil.checkAccess(data.getToken().getAccessToken(),data.getToken().getAccessTokenLevel())){
            return new Reply(Status.NoAccess, "Not logged in");
        } else {
            Player player = (Player) repository.findOne(data.getId());
            if (player == null){
                return new Reply(Status.NotFound, "Player doesnt exist");
            }
            repository.save(player);
            return new Reply(Status.Ok, "Password succesfully changed");
        }
    }

    @Override
    public Reply update(UpdateAccount data) {
        if (!AccessTokenUtil.checkAccess(data.getToken().getAccessToken(),data.getToken().getAccessTokenLevel())){
            return new Reply(Status.NoAccess, "Not logged in");
        }
        else {
            Player player = (Player) repository.findOne(PlayerSpecification.getByUsername(data.getUsername()));
            if (player == null) {
                return new Reply(Status.NotFound, "Player doesnt exist");
            }
            repository.save(player);
            return new Reply(Status.Ok, "Account successfully updated");
        }
    }

    @Override
    public Reply getAccount(Account data) {
        if (!AccessTokenUtil.checkAccess(data.getToken().getAccessToken(),data.getToken().getAccessTokenLevel())){
            return new Reply(Status.NoAccess, "Not logged in");
        }
        else {
            Player player = (Player) repository.findOne(data.getId());
            if (player == null) {
                return new Reply(Status.NotFound, "Player doesnt exist");
            }
            Gson gson = new Gson();
            String account = gson.toJson(player);
            return new Reply(Status.Ok, account);
        }
    }

    @Override
    public Reply holidayReplacement(HolidayReplacement data) {
        if (!AccessTokenUtil.checkAccess(data.getToken().getAccessToken(),data.getToken().getAccessTokenLevel())){
            return new Reply(Status.NoAccess, "Not logged in");
        }
        else {
            Player player = (Player) repository.findOne(PlayerSpecification.getByUsername(data.getUsername()));
            if (player == null) {
                return new Reply(Status.NotFound, "Player doesnt exist");
            }
            //Todo: add holiday replacement here
            return new Reply(Status.Error, "Not yet implemented");
        }
    }

    @Override
    public Reply delete(Delete data) {
        if (!AccessTokenUtil.checkAccess(data.getToken().getAccessToken(),data.getToken().getAccessTokenLevel())){
            return new Reply(Status.NoAccess, "Not logged in");
        }
        else {
            Player player = (Player) repository.findOne(PlayerSpecification.getByUsername(data.getUsername()));
            if (player == null){
                return new Reply(Status.NotFound, "Player doesnt exist");
            }
            repository.delete(PlayerSpecification.getByUsername(data.getUsername()));
            return new Reply(Status.Ok, "Player succesfully deleted");
        }
    }

    @Override
    public AccessToken generateAccessToken(Player player){
        return AccessTokenUtil.newToken(player, AccessTokenLevel.LoggedIn);
    }
}
