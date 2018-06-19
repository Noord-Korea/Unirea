package com.server.tick;

import com.logging.LogLevel;
import com.logging.Logger;

public class RecruitingTick implements Runnable {
    public void update() {
        Logger.getInstance().log("RecruitingTick Running", LogLevel.INFORMATION);
    }

    @Override
    public void run() {
        Logger.getInstance().log("RecruitingTick Running", LogLevel.INFORMATION);

    }
}
