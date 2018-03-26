package com.server;

import com.dbal.repository.ClanRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.specification.PlayerSpecification;
import com.models.Clan;
import com.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class main {
    public static void main(String[] args) {
        //Stop the very annoying "spam" from hibernate
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        PlayerRepository playerRepository = new PlayerRepository();
        ClanRepository clanRepository = new ClanRepository();

        

    }
}