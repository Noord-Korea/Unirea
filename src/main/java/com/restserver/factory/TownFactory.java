package com.restserver.factory;

import com.models.Player;
import com.models.Town;

public class TownFactory {
    public static Town createTown(Player player){
        if(player == null){
            throw new IllegalArgumentException("Player is null");
        }
        
        return null;
    }
}
