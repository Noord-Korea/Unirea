package com.server;

import com.server.exception.GameNotRunningException;
import com.server.tick.*;

public class Game {

    private boolean run;
    private Runnable building;
    private Runnable recruiting;
    private Runnable resource;
    private Runnable troopMovement;

    public Game() {
        run = false;
        building = new BuildingTick();
        recruiting = new RecruitingTick();
        resource = new ResourceTick();
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

        building.run();
        resource.run();
        recruiting.run();
        troopMovement.run();
    }
}
