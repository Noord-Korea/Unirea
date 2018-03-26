package com.server;

import com.dbal.repository.ClanRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.specification.PlayerSpecification;
import com.models.Clan;
import com.models.Player;
import com.server.tick.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class main {
    public static void main(String[] args) {
        //Stop the very annoying "spam" from hibernate
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        PlayerRepository playerRepository = new PlayerRepository();
        ClanRepository clanRepository = new ClanRepository();

        Tick building =     new Building();
        Tick recruiting =   new Recruiting();
        Tick resource =     new Resource();
        Tick troopMovement = new TroopMovement();

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

        exec.scheduleAtFixedRate(resource::update, 0, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(building::update, 0, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(recruiting::update, 0, 5, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(troopMovement::update, 0, 5, TimeUnit.SECONDS);

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