package com.server.tick;

public class RecruitingTick implements Runnable{
    public void update() {
        System.out.println("RecruitingTick Running");
    }

    @Override
    public void run() {
        System.out.println("RecruitingTick Running");
    }
}
