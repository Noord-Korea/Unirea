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
import java.util.List;
import java.util.logging.Level;

public class main {
    public static void main(String[] args) {
        //Stop the very annoying "spam" from hibernate
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        PlayerRepository playerRepository = new PlayerRepository();
        ClanRepository clanRepository = new ClanRepository();

        System.out.println(1);

        Clan clan = new Clan("Noord-Korea");

        System.out.println(2);

        Player player = new Player("Azho", "bramkempen@gmail.com", "test123");
        playerRepository.save(player);

        System.out.println(3);

        clan.addPlayer(player);
        clanRepository.save(clan);

        System.out.println(4);

        player.setUsername("Wauw");
        playerRepository.save(player);

        System.out.println(5);

        List<Clan> clans = clanRepository.findAll();
        for (Clan row: clans) {
            System.out.println(clan.getName());
            for (Player rowPlayer: row.getPlayers()) {
                System.out.println(rowPlayer.getUsername());
            }
        }

    }
}