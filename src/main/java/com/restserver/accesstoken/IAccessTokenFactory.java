package com.restserver.accesstoken;

import com.models.AccessToken;
import com.models.Player;

public interface IAccessTokenFactory {
    AccessToken newToken(Player player);

}
