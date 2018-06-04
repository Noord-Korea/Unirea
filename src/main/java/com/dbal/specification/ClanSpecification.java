package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class ClanSpecification {
    private ClanSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getbyName(String name) {

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("name", name);
            }
        };
    }
}