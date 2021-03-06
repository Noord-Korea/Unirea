package com.restserver.utils.accesstoken;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.specification.AccessTokenSpecification;
import com.models.AccessToken;
import com.models.Player;
import com.restserver.factory.accesstoken.AccessTokenFactory;
import com.restserver.factory.accesstoken.IAccessTokenFactory;

public abstract class AccessTokenUtil {

    private static IAccessTokenFactory accessTokenFactory = new AccessTokenFactory();
    private static AccessTokenRepository accessTokenRepository = new AccessTokenRepository();

    private AccessTokenUtil() {

    }

    public static boolean checkAccess(String accessTokenString, AccessTokenLevel accessLevel) {
        if (accessLevel == null || accessTokenString.isEmpty()) {
            throw new IllegalArgumentException("AccessTokenString is empty or AccessTokenLevel is null");
        }

        AccessToken accessToken = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(accessTokenString));

        return checkAccess(accessToken, accessLevel);
    }

    public static boolean checkAccess(AccessToken accessToken, AccessTokenLevel accessLevel) {
        if (accessLevel == null) {
            throw new IllegalArgumentException("AccessToken is null or AccessTokenLevel is null");
        }

        if (accessToken == null) {
            return accessLevel == AccessTokenLevel.NOLOGIN;
        }

        if (accessToken.isExpired()) {
            return accessLevel == AccessTokenLevel.NOLOGIN;
        }
        accessToken.refresh();
        return true;
    }

    public static Player getPlayerFromToken(String accessTokenString) {
        AccessToken accessToken = accessTokenRepository.findOne(AccessTokenSpecification.getByAccessToken(accessTokenString));
        return accessToken != null ? accessToken.getPlayer() : null;
    }

    public static AccessToken newToken(Player player, AccessTokenLevel tokenLevel) {
        return accessTokenFactory.newToken(player, tokenLevel);
    }
}
