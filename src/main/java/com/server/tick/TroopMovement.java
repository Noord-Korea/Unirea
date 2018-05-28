package com.server.tick;

import com.logging.LogLevel;
import com.logging.Logger;

public class TroopMovement implements Runnable {


    public void update() {
        Logger.getInstance().log("Troopmovement Running", LogLevel.INFORMATION);
    }

    @Override
    public void run() {
        Logger.getInstance().log("Troopmovement Running", LogLevel.INFORMATION);

    }
}
