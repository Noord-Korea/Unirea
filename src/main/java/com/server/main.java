package com.server;

import com.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.text.MessageFormat;
import java.util.List;

public class main {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration().configure();
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory factory = configuration.buildSessionFactory(builder.build());
        final Session session = factory.openSession();

        final Player player = new Player("Azho", "bramkempen@gmail.com", "test123");

        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        final List<Player> playerList = session.createCriteria(Player.class).list();
        System.out.println("\n----\n");
        System.out.println(MessageFormat.format("Storing {0} players in the database", playerList.size()));
        for (final Player p : playerList) {
            System.out.println(p);
        }
        System.out.println("\n----\n");
        session.close();
        factory.close();
    }
}