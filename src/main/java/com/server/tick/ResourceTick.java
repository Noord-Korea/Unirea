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

        TownRepository tmp = new TownRepository();

        Town town = tmp.findOne(1);
        System.out.println("Got Town");
        if(town == null){
            System.out.println("Town is null");
        }else {
            System.out.println(town.getName());
        }
        /*
        for (Town town : towns) {
            System.out.println(town.getName());
            /*for (TownResources townResources : town.getTownResources()) {
                //townResources.setValue(townResources.getValue()+1);
                //System.out.println(townResources.getValue());
            }*/
            //townRepository.save(town);

    }
}
