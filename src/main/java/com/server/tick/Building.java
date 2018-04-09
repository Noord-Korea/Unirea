package com.server.tick;

public class Building implements Runnable{
    public void update() {
        System.out.println("Building Running");
    }

    @Override
    public void run() {
        System.out.println("Building Running");
    }
}
