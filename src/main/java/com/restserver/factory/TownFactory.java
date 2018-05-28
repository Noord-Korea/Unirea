package com.restserver.factory;

import com.dbal.repository.TownRepository;
import com.models.Player;
import com.models.Town;
import com.restserver.exception.PlayerHasTownException;

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
