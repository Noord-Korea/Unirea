package com.dbal.specification;

import com.dbal.Criterial;
import org.hibernate.criterion.Criterion;

public interface Specifiable extends Criterial {

    public Criterion toCriterion();

    public Specifiable and(final Specifiable specification);

    public Specifiable or(final Specifiable specification);

    public Specifiable not(final Specifiable specification);

}