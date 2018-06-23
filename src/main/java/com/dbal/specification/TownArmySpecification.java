package com.dbal.specification;

import com.models.TownArmyId;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class TownArmySpecification {
    private TownArmySpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByArmyPk(TownArmyId pk) {
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("pk", pk);
            }
        };
    }
}
