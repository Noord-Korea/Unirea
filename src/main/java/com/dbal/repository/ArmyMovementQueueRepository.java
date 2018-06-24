package com.dbal.repository;

import com.models.ArmyMovementQueue;

public class ArmyMovementQueueRepository extends AbstractRepository<ArmyMovementQueue, Integer>{
    @Override
    public Class<ArmyMovementQueue> getDomainClass() {
        return ArmyMovementQueue.class;
    }
}
