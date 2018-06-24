package com.dbal.repository;

import com.models.TownArmy;

public class TownArmyRepository extends AbstractRepository<TownArmy, Integer> {
    @Override
    public Class<TownArmy> getDomainClass() {
        return TownArmy.class;
    }
}
