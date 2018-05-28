package com.restserver.handler;


import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.IRepository;
import com.dbal.specification.AccessTokenSpecification;
import com.models.AccessToken;
import com.models.Player;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.utils.accesstoken.AccessTokenLevel;
import com.restserver.utils.accesstoken.AccessTokenUtil;

public class TownHandler implements ITownHandler {
    private IRepository townRepository;
    private AccessTokenRepository accessTokenRepository;

    public TownHandler(IRepository townRepository, IRepository accessTokenSpecification) {
        this.townRepository = townRepository;
        this.accessTokenRepository = (AccessTokenRepository) accessTokenSpecification;
    }

    public Reply getTown(){
        return new Reply(Status.NotFound, "Not yet implemented");
    }

    @Override
    public Reply createTown(BaseTownRequest baseTownRequest) {
        AccessToken accessToken = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(baseTownRequest.getToken()));
        if(!AccessTokenUtil.checkAccess(accessToken, AccessTokenLevel.LOGGEDIN)){
            return new Reply(Status.NoAuth, "Accesstoken not valid");
        }
        return null;
    }
}