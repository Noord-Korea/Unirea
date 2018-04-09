package com.server.tick;

import com.dbal.repository.ResourceRepository;
import com.dbal.repository.TownRepository;
import com.models.Town;
import com.models.TownResources;

import java.util.List;

public class ResourceTick implements Runnable{
    private ResourceRepository resourceRepository;
    private TownRepository townRepository;

    public ResourceTick() {
        resourceRepository = new ResourceRepository();
        townRepository = new TownRepository();
    }

    @Override
    public void run() {
        System.out.println("ResourceTick Running");

        List<Town> towns = this.townRepository.findAll();
        for (Town town : towns) {
            for (TownResources townResources : town.getTownResources()) {
                townResources.setValue(townResources.getValue()+1);
            }
        }
    }
}
