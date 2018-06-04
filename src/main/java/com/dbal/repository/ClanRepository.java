package com.dbal.repository;

import com.models.Clan;

public class ClanRepository extends AbstractRepository<Clan, Integer> {
    @Override
    public Class<Clan> getDomainClass() {
        return Clan.class;
    }

}
