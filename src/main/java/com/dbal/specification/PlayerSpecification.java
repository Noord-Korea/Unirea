package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class PlayerSpecification {
    public static Specifiable getByEmail(String email){
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("email", email);
            }
        };
    }
}
