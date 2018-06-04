package com.dbal.repository;

import com.models.TownBuilding;
import com.models.TownBuildingId;

public class TownBuildingIdRepository extends AbstractRepository<TownBuildingId, Integer> {
    @Override
    public Class<TownBuildingId> getDomainClass() {
        return TownBuildingId.class;
    }
}
