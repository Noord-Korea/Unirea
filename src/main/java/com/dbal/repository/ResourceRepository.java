package com.dbal.repository;

import com.models.Resource;

public class ResourceRepository extends AbstractRepository<Resource, Integer> {
    @Override
    public Class<Resource> getDomainClass() {
        return Resource.class;
    }
}
