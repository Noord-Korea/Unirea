package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class ArmyMovementQueueSpecification {
    private ArmyMovementQueueSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByHomeTownId(int townId) {
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("homeTownId", townId);
            }
        };
    }
}
