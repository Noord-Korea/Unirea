package com.dbal.repository;

import com.models.Player;


public class PlayerRepository extends AbstractRepository<Player, Integer> {
    @Override
    public Class<Player> getDomainClass() {
        return Player.class;
    }

}
