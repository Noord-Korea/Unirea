package com.dbal.repository;

import com.models.Building;

public class BuildingRepository extends AbstractRepository<Building, Integer> {
    @Override
    public Class<Building> getDomainClass() {
        return Building.class;
    }
}
