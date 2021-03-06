package com.restserver;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.repository.TownRepository;
import com.restserver.handler.*;
import com.restserver.services.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.logging.Level;

public class RestServer {
    public static void main(String[] args) throws Exception {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.ALL);
        ServletContextHandler context = new
                ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8090);

        //region Origin header
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        //endregion

        jettyServer.setHandler(context);
        ServletHolder jerseyServlet =
                context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        AccountHandler accountHandler = new AccountHandler(new PlayerRepository(), new AccessTokenRepository());
        AccountService.setHandler(accountHandler);

        MapHandler mapHandler = new MapHandler();
        MapService.setHandler(mapHandler);

        ArmyHandler armyHandler = new ArmyHandler();
        ArmyService.setHandler(armyHandler);

        BuildingHandler buildingHandler = new BuildingHandler();
        BuildingService.setHandler(buildingHandler);

        TownHandler townHandler = new TownHandler(new TownRepository());
        TownService.setHandler(townHandler);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages",
                "com.restserver.services");


        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
