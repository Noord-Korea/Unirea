package com.dbal.repository;

import com.models.TownBuildingId;

public class TownBuildingIdRepository extends AbstractRepository<TownBuildingId, Integer> {
    @Override
    public Class<TownBuildingId> getDomainClass() {
        return TownBuildingId.class;
    }
}
