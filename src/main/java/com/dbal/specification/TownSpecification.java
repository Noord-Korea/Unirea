package com.dbal.specification;

import com.dbal.repository.PlayerRepository;
import com.models.Player;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class TownSpecification {

    private TownSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByName(String name) {

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("name", name);
            }
        };
    }

    public static Specifiable getById(int id) {

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("id", id);
            }
        };
    }

    public static Specifiable getByPlayerId(int id) {
        PlayerRepository repo = new PlayerRepository();
        Player player = repo.findOne(id);
        return getByPlayer(player);
    }

    public static Specifiable getByPlayer(Player player) {
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("player", player);
            }
        };
    }
}
