package com.dbal.repository;

import com.models.ArmyQueue;

public class ArmyQueueRepository extends AbstractRepository<ArmyQueue, Integer> {
    @Override
    public Class<ArmyQueue> getDomainClass() {
        return ArmyQueue.class;
    }
}
