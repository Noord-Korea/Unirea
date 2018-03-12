package com.server;

import com.server.exception.GameNotRunningException;
import com.server.tick.*;

public class Game {

    private boolean run;
    private Tick building;
    private Tick recruiting;
    private Tick resource;
    private Tick troopMovement;

    public Game() {
        run = false;
        building = new Building();
        recruiting = new Recruiting();
        resource = new Resource();
        troopMovement = new TroopMovement();
    }

    public void start(){
        run = true;
    }

    /**
     * This will execute the game tick.
     * The order is: building, resources, recruiting and troopmovement
     * @throws GameNotRunningException if the game isn't started
     */
    public void tick() throws GameNotRunningException {
        if(!run){
            throw new GameNotRunningException();
        }

        building.update();
        resource.update();
        recruiting.update();
        troopMovement.update();
    }
}
