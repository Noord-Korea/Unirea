package com.server.tick;

import com.logging.LogLevel;
import com.logging.Logger;

public class BuildingTick implements Runnable {
    public void update() {
        Logger.getInstance().log("BuildingTick Running", LogLevel.INFORMATION);
    }

    @Override
    public void run() {
        Logger.getInstance().log("BuildingTick Running", LogLevel.INFORMATION);

    }
}
