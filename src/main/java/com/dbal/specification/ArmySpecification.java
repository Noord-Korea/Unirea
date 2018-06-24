package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class ArmySpecification {
    private ArmySpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByArmyID(int id) {
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("id", id);
            }
        };
    }
}
