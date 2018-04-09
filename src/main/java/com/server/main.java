package com.server;

import com.dbal.repository.ClanRepository;
import com.dbal.repository.PlayerRepository;
import com.server.tick.Building;
import com.server.tick.Recruiting;
import com.server.tick.Resource;
import com.server.tick.TroopMovement;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        //Stop the very annoying "spam" from hibernate
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        PlayerRepository playerRepository = new PlayerRepository();
        ClanRepository clanRepository = new ClanRepository();

        Runnable building =     new Building();
        Runnable recruiting =   new Recruiting();
        Runnable resource =     new Resource();
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
        }
    }
}