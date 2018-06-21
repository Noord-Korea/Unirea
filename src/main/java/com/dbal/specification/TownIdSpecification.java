package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class TownIdSpecification {

    private TownIdSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByTownId(int townId) {

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("TOWN_ID", townId);
            }
        };
    }

}
