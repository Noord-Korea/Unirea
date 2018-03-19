package com.server;

import com.dbal.repository.PlayerRepository;
import com.dbal.specification.PlayerSpecification;
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
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Player player = new Player("Azho", "bramkempen@gmail.com", "test123");
        PlayerRepository playerRepository = new PlayerRepository();
        playerRepository.save(player);

        player = playerRepository.findOne(10);
        player.setUsername("Wauw");
        playerRepository.save(player);

        List<Player> players = playerRepository.findAll(PlayerSpecification.getByUsername("Azho"));
        for (Player row: players) {
            System.out.println(row.getId() + " " + row.getUsername());
        }
    }
}