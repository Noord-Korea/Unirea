package com.dbal.repository;

import com.models.Army;

public class ArmyRepository extends AbstractRepository<Army, Integer> {
    @Override
    public Class<Army> getDomainClass() {
        return Army.class;
    }
}
