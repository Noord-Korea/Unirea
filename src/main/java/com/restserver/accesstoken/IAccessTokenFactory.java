package com.restserver.accesstoken;

import com.models.Player;

public interface IAccessTokenFactory {
    AccessToken newToken(Player player);

}
