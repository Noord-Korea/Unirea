package com.restserver.handler;


import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.IRepository;
import com.dbal.specification.AccessTokenSpecification;
import com.models.AccessToken;
import com.models.Player;
import com.models.Town;
import com.restserver.exception.PlayerHasTownException;
import com.restserver.factory.TownFactory;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

public class TownHandler implements ITownHandler {
    private IRepository townRepository;
    private AccessTokenRepository accessTokenRepository;

    public TownHandler(IRepository townRepository, IRepository accessTokenRepository) {
        this.townRepository = townRepository;
        this.accessTokenRepository = (AccessTokenRepository) accessTokenRepository;
    }

    public Reply getTown(){
        return new Reply(Status.NOTFOUND, "Not yet implemented");
    }

    @Override
    public Reply createTown(BaseTownRequest baseTownRequest) {
        AccessToken accessToken = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(baseTownRequest.getToken()));
        if(!AccessTokenUtil.checkAccess(accessToken, AccessTokenLevel.LOGGEDIN)){
            return new Reply(Status.NOAUTH, "Accesstoken not valid");
        }
        try {
            Town town = TownFactory.createTown(accessToken.getPlayer());
        } catch (PlayerHasTownException e) {
            return new Reply(Status.ERROR, e.getMessage());
        }
        return null;
    }
}