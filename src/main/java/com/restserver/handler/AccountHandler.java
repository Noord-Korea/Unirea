package com.restserver.handler;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.IRepository;
import com.dbal.specification.AccessTokenSpecification;
import com.dbal.specification.PlayerSpecification;
import com.google.gson.Gson;
import com.models.Player;
import com.models.AccessToken;
import com.restserver.json.request.account.*;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.json.response.account.Info;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

public class AccountHandler implements IAccountHandler {
    private IRepository playerRepository;
    private AccessTokenRepository accessTokenRepository;

    public AccountHandler(IRepository playerRepository, IRepository accessTokenRepository) {
        this.playerRepository = playerRepository;
        this.accessTokenRepository = (AccessTokenRepository) accessTokenRepository;
    }

    @Override
    public Reply login(Login data) {
        Player player = (Player) playerRepository.findOne(PlayerSpecification.getByEmail(data.getEmail()));
        if (player == null) {
            return new Reply(Status.NOTFOUND, "Player doesn't exist");
        } else if (!player.getEmail().equals(data.getEmail()) || !player.checkPassword(data.getPassword())) {
            return new Reply(Status.NOACCESS, "Your login credentials were incorrect");
        }
        AccessToken temp = accessTokenRepository.findOne(AccessTokenSpecification.getByPlayerId(player.getId()));
        if(!(temp == null)){
            return new Reply(Status.CONFLICT, "Accesstoken already exists");
        }
        AccessToken token = generateAccessToken(player);
        accessTokenRepository.save(token);
        return new Reply(Status.OK, token.getAccessToken());

    }

    @Override
    public Reply logout(Logout data) {
        if (data.getToken() == null) {
            return new Reply(Status.NOAUTH, "Player already logged out");
        } else {
            AccessToken token = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(data.getToken()));
            if (token == null) {
                return new Reply(Status.NOTFOUND, "Token doesn't exist");
            }
            accessTokenRepository.delete(token);
            return new Reply(Status.OK, "Player successfully logged out");
        }
    }

    @Override
    public Reply register(Register data) {
        Player player = (Player) playerRepository.findOne(PlayerSpecification.getByEmail(data.getEmail()));
        if (player == null) {
            try {
                player = new Player(data.getUsername(), data.getEmail().toLowerCase(), data.getPassword());
            } catch (IllegalArgumentException e) {
                return new Reply(Status.ERROR, e.getMessage());
            }
            playerRepository.save(player);
            return new Reply(Status.OK, "Successfully registered");
        } else {
            return new Reply(Status.CONFLICT, "Email is already used");
        }
    }

    //Todo: Maybe make an player update function as deleting and creating a player is somewhat brutal
    @Override
    public Reply changePassword(ChangePassword data) {
        if (!(data.getNewPassword().equals(data.getVerifyPassword()))) {
            return new Reply(Status.CONFLICT, "Passwords don't match");
        } else if (!AccessTokenUtil.checkAccess(data.getToken(), AccessTokenLevel.LOGGEDIN)) {
            return new Reply(Status.NOACCESS, "Not logged in");
        } else {
            AccessToken accessToken = (AccessToken) accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(data.getToken()));
            Player player = accessToken.getPlayer();
            if (player == null) {
                return new Reply(Status.NOTFOUND, "Player doesnt exist");
            }
            playerRepository.save(player);
            return new Reply(Status.OK, "Password succesfully changed");
        }
    }

    @Override
    public Reply update(UpdateAccount data) {
        if (!AccessTokenUtil.checkAccess(data.getToken(), AccessTokenLevel.LOGGEDIN)) {
            return new Reply(Status.NOACCESS, "Not logged in");
        } else {
            Player player = (Player) playerRepository.findOne(PlayerSpecification.getByUsername(data.getUsername()));
            if (player == null) {
                return new Reply(Status.NOTFOUND, "Player doesnt exist");
            }
            playerRepository.save(player);
            return new Reply(Status.OK, "Account successfully updated");
        }
    }

    @Override
    public Reply getAccount(Account data) {
        if (!AccessTokenUtil.checkAccess(data.getToken(), AccessTokenLevel.LOGGEDIN)) {
            return new Reply(Status.NOACCESS, "Not logged in");
        } else {
            AccessToken accessToken = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(data.getToken()));
            if (accessToken == null) {
                return new Reply(Status.NOTFOUND, "Player doesnt exist");
            }
            Gson gson = new Gson();
            Player player = accessToken.getPlayer();
            Info info = new Info(player.getId(), accessToken.getAccessToken(), player.getUsername(), player.getEmail());
            String account = gson.toJson(info);
            return new Reply(Status.OK, account);
        }
    }

    @Override
    public Reply holidayReplacement(HolidayReplacement data) {
        if (!AccessTokenUtil.checkAccess(data.getToken(), AccessTokenLevel.LOGGEDIN)) {
            return new Reply(Status.NOACCESS, "Not logged in");
        } else {
            Player player = (Player) playerRepository.findOne(PlayerSpecification.getByUsername(data.getUsername()));
            if (player == null) {
                return new Reply(Status.NOTFOUND, "Player doesnt exist");
            }
            //Todo: add holiday replacement here
            return new Reply(Status.ERROR, "Not yet implemented");
        }
    }

    @Override
    public Reply delete(Delete data) {
        if (!AccessTokenUtil.checkAccess(data.getToken(), AccessTokenLevel.LOGGEDIN)) {
            return new Reply(Status.NOACCESS, "Not logged in");
        } else {
            Player player = (Player) playerRepository.findOne(PlayerSpecification.getByUsername(data.getUsername()));
            if (player == null) {
                return new Reply(Status.NOTFOUND, "Player doesnt exist");
            }
            playerRepository.delete(PlayerSpecification.getByUsername(data.getUsername()));
            return new Reply(Status.OK, "Player succesfully deleted");
        }
    }

    @Override
    public AccessToken generateAccessToken(Player player) {
        return AccessTokenUtil.newToken(player, AccessTokenLevel.LOGGEDIN);
    }
}
