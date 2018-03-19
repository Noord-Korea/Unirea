package com.dbal;

import org.hibernate.Criteria;

public interface Criterial {

    public Criteria toCriteria(Criteria criteria);

}