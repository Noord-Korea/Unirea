package com.restserver.factory;

import com.dbal.repository.TownRepository;
import com.models.Building;
import com.models.Player;
import com.models.Town;
import com.models.TownBuildings;
import com.restserver.exception.PlayerHasTownException;

import java.util.HashSet;
import java.util.Set;

public class TownFactory implements ITownFactory {
    private static TownRepository townRepository = new TownRepository();

    public static Town createTown(Player player) throws PlayerHasTownException {
        if(player == null){
            throw new IllegalArgumentException("Player is null");
        }
        if(player.getTowns().size() != 0){
            throw new PlayerHasTownException();
        }

        Town town = new Town(player, "My first town");

        //TODO: add basic buildings

        //TODO: add resources

        townRepository.save(town);
        return town;
    }
}
