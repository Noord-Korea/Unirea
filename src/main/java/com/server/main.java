package com.server;

import com.Logging.Logger;
import com.dbal.repository.*;
import com.models.Resource;
import com.models.Town;
import com.models.TownResources;
import com.server.tick.BuildingTick;
import com.server.tick.RecruitingTick;
import com.server.tick.ResourceTick;
import com.server.tick.TroopMovement;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        //Stop the very annoying "spam" from hibernate
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        IRepository playerRepository = new PlayerRepository();
        IRepository clanRepository = new ClanRepository();

        IRepository townRepository = new TownRepository();
        IRepository resourceRepository = new ResourceRepository();

        Runnable building = new BuildingTick();
        Runnable recruiting = new RecruitingTick();
        Runnable resourceTick = new ResourceTick();
        Runnable troopMovement = new TroopMovement();

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(4);

        exec.scheduleAtFixedRate(building, 5, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(recruiting, 5, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(troopMovement, 5, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(resourceTick, 5, 5, TimeUnit.SECONDS);


        Resource resource1 = new Resource();
        resource1.setName("Hout");
        resourceRepository.save(resource1);

        Resource resource2 = new Resource();
        resource2.setName("Banaan");
        resourceRepository.save(resource2);

        Town town10 = new Town();
        town10.setName("Town");
        townRepository.save(town10);

        boolean running = true;
        int i = 0;
        while (running) {
            System.out.println("Main thread");
            running = i != 100;

            Town town1 = new Town();
            town1.setName("Bram");
            townRepository.save(town1);

            TownResources townResources1 = new TownResources();
            townResources1.setTown(town1);
            townResources1.setResource(resource1);
            townResources1.setValue(10);

            TownResources townResources2 = new TownResources();
            townResources2.setTown(town1);
            townResources2.setResource(resource2);
            townResources2.setValue(12);

            town1.addTownResource(townResources1);
            town1.addTownResource(townResources2);
            townRepository.save(town1);


            i++;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.log(e);
            }
        }
    }
}