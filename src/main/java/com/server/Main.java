package com.server;

import com.logging.LogLevel;
import com.logging.Logger;
import com.dbal.repository.*;
import com.server.tick.BuildingTick;
import com.server.tick.RecruitingTick;
import com.server.tick.ResourceTick;
import com.server.tick.TroopMovement;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Main {
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

        boolean running = true;
        int i = 0;
        while (running) {
            logger.log("Main thread", LogLevel.DEBUG);
            running = i != 100;
            i++;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.log(e);
            }
        }
    }
}