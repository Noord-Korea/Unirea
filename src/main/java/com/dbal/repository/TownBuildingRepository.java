package com.dbal.repository;

import com.models.Town;
import com.models.TownBuilding;

public class TownBuildingRepository extends AbstractRepository<TownBuilding, Integer> {
    @Override
    public Class<TownBuilding> getDomainClass() {
        return TownBuilding.class;
    }
}
