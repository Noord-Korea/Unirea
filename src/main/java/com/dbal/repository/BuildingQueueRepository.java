package com.dbal.repository;

import com.models.BuildingQueue;

public class BuildingQueueRepository extends AbstractRepository<BuildingQueue, Integer> {
    @Override
    public Class<BuildingQueue> getDomainClass() {
        return BuildingQueue.class;
    }
}
