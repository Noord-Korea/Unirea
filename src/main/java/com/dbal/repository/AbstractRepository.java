package com.dbal.repository;

import com.dbal.HibernateUtil;
import com.dbal.Util;
import com.dbal.specification.Specifiable;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractRepository<T, Id extends Serializable> {

    private static String entityNull = "Entity can't be null";
    public abstract Class<T> getDomainClass();

    public Session openSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    public void delete(Id id) {

        T entity = findOne(id);

        if(entity == null){
            throw new IllegalArgumentException(entityNull);
        }

        delete(entity);
    }

    public void delete(T entity) {
        if(entity == null){
            throw new IllegalArgumentException(entityNull);
        }

        Session session = openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        } catch (RuntimeException e) {
            Util.logException(e);
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }

    }

    public void delete(Iterable<? extends T> entities) {

        if(entities == null){
            throw new IllegalArgumentException("Entities can't be null");
        }

        Session session = openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            entities.forEach(session::delete);
            tx.commit();
        } catch (RuntimeException e) {
            Util.logException(e);
            try {
                if(tx != null)
                    tx.rollback();
            } catch (HibernateException he) {
                Util.logException(he);
            }
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public T findOne(Id id) {

        Class<T> classType = getDomainClass();
        T entity = null;

        Session session = openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            entity = (T) session.get(classType, id);
            tx.commit();
        } catch (RuntimeException e) {
            Util.logException(e);
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }

        return entity;
    }

    public T findOne(Specifiable spec) {

        Session session = openSession();

        Class<T> classType = getDomainClass();
        Criteria criteria = session.createCriteria(classType);

        criteria = buildCriteria(criteria, spec);

        T entity;

        try {
            entity = (T) criteria.uniqueResult();
            return entity;
        } catch (HibernateException he) {
            Util.logException(he);
        } finally {
            session.close();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findAll(null);
    }

    public List<T> findAll(Specifiable spec) {
        Session session = openSession();

        Class<T> classType = getDomainClass();
        Criteria criteria = session.createCriteria(classType);
        if(spec != null) {
            //criteria = buildCriteria(criteria, spec);
        }

        List<T> entities;

        try {
            entities = criteria.list();
            return entities;
        } catch (HibernateException he) {
            Util.logException(he);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    public <S extends T> S save(S entity) {

        if(entity == null){
            throw new IllegalArgumentException(entityNull);
        }

        Session session = openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            return entity;
        } catch (RuntimeException e) {
            Util.logException(e);
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }

        return null;
    }

    @Transactional
    public <S extends T> List<S> save(Iterable<S> entities) {

        if(entities == null){
            throw new IllegalArgumentException("Entities can't be null");
        }

        List<S> result = new ArrayList<S>();

        Session session = openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            entities.forEach(entity -> {
                session.saveOrUpdate(entity);
                result.add(entity);
            });
            tx.commit();
            return result;
        } catch (RuntimeException e) {
            Util.logException(e);
            try {
                if(tx != null)
                    tx.rollback();
            } catch (HibernateException he) {
                Util.logException(he);
            }
        } finally {
            session.close();
        }

        return null;
    }

    private Criteria buildCriteria(Criteria criteria, Specifiable spec) {

        if (spec != null) {
            spec.toCriteria(criteria);
        }

        return criteria;
    }
}
