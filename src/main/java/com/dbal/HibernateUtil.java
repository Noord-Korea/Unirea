package com.dbal;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static boolean initSessionFactory() {

        Session session = sessionFactory.openSession();

        try {
            session.createSQLQuery("SELECT 1;").list();
            Util.logInfo("Connection initialization is completed.");
            return true;
        } catch (RuntimeException ex) {
            Util.logException(ex);
        } finally {
            session.close();
        }

        Util.logError("Connection initialization failed.");
        return false;
    }


    public static void closeSessionFactory() {
        sessionFactory.close();
        Util.logInfo("Connection and Session Factory is closed.");
    }
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
            return configuration.buildSessionFactory(standardServiceRegistry);
        }
        catch (HibernateException ex) {
            System.err.println("Session factory initialization failed. " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}
