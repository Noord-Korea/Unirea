package com.dbal.specification;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class AbstractSpecification implements Specifiable {

    public abstract Criterion toCriterion();

    public Specifiable and(final Specifiable specification) {

        return new AndSpecification(this, specification);
    }

    public Specifiable or(final Specifiable specification) {

        return new OrSpecification(this, specification);
    }

    public Specifiable not(final Specifiable specification) {

        return new NotSpecification(specification);
    }

    public Criteria toCriteria(Criteria criteria) {
        return criteria.add(toCriterion());
    }

    private class AndSpecification extends AbstractSpecification {

        private Specifiable spec1;
        private Specifiable spec2;

        public AndSpecification(final Specifiable spec1, final Specifiable spec2) {
            this.spec1 = spec1;
            this.spec2 = spec2;
        }

        public Criterion toCriterion() {
            return Restrictions.and(this.spec1.toCriterion(), this.spec2.toCriterion());
        }
    }

    private class OrSpecification extends AbstractSpecification {

        private Specifiable spec1;
        private Specifiable spec2;

        public OrSpecification(final Specifiable spec1, final Specifiable spec2) {

            this.spec1 = spec1;
            this.spec2 = spec2;
        }

        public Criterion toCriterion() {

            return Restrictions.or(this.spec1.toCriterion(), this.spec2.toCriterion());
        }
    }

    private class NotSpecification extends AbstractSpecification {

        private Specifiable spec1;

        public NotSpecification(final Specifiable spec1) {

            this.spec1 = spec1;
        }

        public Criterion toCriterion() {

            return Restrictions.not(this.spec1.toCriterion());
        }
    }
}
