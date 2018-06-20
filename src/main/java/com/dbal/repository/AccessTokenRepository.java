package com.dbal.repository;

import com.models.AccessToken;

public class AccessTokenRepository extends AbstractRepository<AccessToken, Integer> {
    @Override
    public Class<AccessToken> getDomainClass() {
        return AccessToken.class;
    }
}
