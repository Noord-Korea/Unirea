package com.server;

import com.dbal.repository.ResourceRepository;
import com.dbal.repository.TownRepository;
import com.models.Resource;
import com.models.Town;
import com.models.TownResources;

public class main {
    public static void main(String[] args) {
        //Stop the very annoying "spam" from hibernate
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    /*    PlayerRepository playerRepository = new PlayerRepository();
        ClanRepository clanRepository = new ClanRepository();

        Runnable building =     new BuildingTick();
        Runnable recruiting =   new RecruitingTick();
        Runnable resource =     new ResourceTick();
        Runnable troopMovement = new TroopMovement();

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

        exec.scheduleAtFixedRate(resource, 0, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(building, 0, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(recruiting, 0, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(troopMovement, 0, 5, TimeUnit.SECONDS);

        boolean running = true;
        int i = 0;
        while (running){
            System.out.println("Main thread");
            running = i != 100;
            i++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        TownRepository townRepository = new TownRepository();
        ResourceRepository resourceRepository = new ResourceRepository();
        Town town = new Town();
        town.setName("Bram");
        townRepository.save(town);
        Resource resource = new Resource();
        resource.setName("hout");
        resourceRepository.save(resource);

        TownResources townResources = new TownResources();
        townResources.setTown(town);
        townResources.setResource(resource);
        townResources.setValue(10);

        town.addTownResource(townResources);
        townRepository.save(town);
    }
}