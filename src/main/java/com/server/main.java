package com.server;

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

        final Configuration configuration = new Configuration().configure();
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory factory = configuration.buildSessionFactory(builder.build());
        final Session session = factory.openSession();


        //region addPlayer
        final Player player = new Player("Azho", "bramkempen@gmail.com", "test123");

        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        //endregion

        //region list of players
        final List<Player> playerList = session.createCriteria(Player.class).list();
        System.out.println("\n----\n");
        System.out.println(MessageFormat.format("Storing {0} players in the database", playerList.size()));
        for (final Player p : playerList) {
            System.out.println(p);
        }
        System.out.println("\n----\n");
        //endregion

        //Close session
        session.close();
        factory.close();
    }
}