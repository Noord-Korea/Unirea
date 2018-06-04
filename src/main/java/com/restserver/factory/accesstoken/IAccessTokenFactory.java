package com.restserver.factory.accesstoken;

import com.models.AccessToken;
import com.models.Player;
import com.restserver.utils.accesstoken.AccessTokenLevel;

public interface IAccessTokenFactory {
    AccessToken newToken(Player player, AccessTokenLevel tokenLevel);

}
