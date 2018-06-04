package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class PlayerSpecification {
    private PlayerSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByEmail(String email) {
        if(email == null)
            throw new IllegalArgumentException();

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("email", email.toLowerCase());
            }
        };
    }

    public static Specifiable getByUsername(String username) {

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("username", username);
            }
        };
    }
}
