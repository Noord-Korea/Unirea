package com.server.tick;

public class BuildingTick implements Runnable{
    public void update() {
        System.out.println("BuildingTick Running");
    }

    @Override
    public void run() {
        System.out.println("BuildingTick Running");
    }
}
