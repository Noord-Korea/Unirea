package com.dbal.repository;

import com.models.Town;

public class TownRepository extends AbstractRepository<Town, Integer> {
    @Override
    public Class<Town> getDomainClass() {
        return Town.class;
    }
}
