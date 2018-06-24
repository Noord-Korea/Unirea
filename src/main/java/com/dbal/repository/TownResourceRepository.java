package com.dbal.repository;

import com.models.TownResources;

public class TownResourceRepository extends AbstractRepository<TownResources, Integer> {
    @Override
    public Class<TownResources> getDomainClass() {
        return TownResources.class;
    }
}
