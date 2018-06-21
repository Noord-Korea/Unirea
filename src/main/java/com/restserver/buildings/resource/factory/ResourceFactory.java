package com.restserver.buildings.resource.factory;

import com.dbal.repository.IRepository;
import com.dbal.specification.ResourceSpecification;
import com.models.Resource;
import com.restserver.buildings.resource.ResourceType;

public class ResourceFactory {
    private IRepository repository;

    public ResourceFactory(IRepository repository) {
        this.repository = repository;
    }

    public Resource getResourceWithResourceType(ResourceType resourceType){
        return (Resource) repository.findOne(ResourceSpecification.getByName(resourceType.name()));
    }
}
