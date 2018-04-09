package com.server.tick;

public class Resource implements Runnable{
    public void update() {
        System.out.println("Resource Running");
    }

    @Override
    public void run() {
        System.out.println("Resource Running");
    }
}
