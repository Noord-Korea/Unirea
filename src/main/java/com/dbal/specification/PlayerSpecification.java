package com.dbal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class PlayerSpecification {
    private PlayerSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByEmail(String email){
        if(email.equals("")){
            throw new IllegalArgumentException("Please fill in an email-address!");
        }
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("email", email);
            }
        };
    }

    public static Specifiable getByUsername(String username){
        if(username.equals("")){
            throw new IllegalArgumentException("Please fill in an username!");
        }
        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("username", username);
            }
        };
    }
}
