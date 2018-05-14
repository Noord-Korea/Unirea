package com.restserver.utils.accesstoken;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.specification.AccessTokenSpecification;
import com.models.AccessToken;
import com.models.Player;

public class AccessTokenUtil {
    public static boolean checkAccess(String accessTokenString, AccessTokenLevel accessLevel){
        AccessTokenRepository accessTokenRepository = new AccessTokenRepository();
        AccessToken accessToken = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(accessTokenString));

        if(accessToken == null){
            if(accessLevel == AccessTokenLevel.NoLogin){
                return true;
            }
            return false;
        }
        return false;
    }
}
