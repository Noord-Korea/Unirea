package com.server.tick;

public class ResourceTick implements Runnable{
    public void update() {
        System.out.println("ResourceTick Running");
    }

    @Override
    public void run() {
        System.out.println("ResourceTick Running");
    }
}
