package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class ResourceSpecification {
    private ResourceSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByName(String name) {

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("name", name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
            }
        };
    }
}
