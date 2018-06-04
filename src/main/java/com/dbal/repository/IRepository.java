package com.dbal.repository;

import com.dbal.specification.Specifiable;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface IRepository<T, Id extends Serializable> {
    Class<T> getDomainClass();

    Session openSession();

    void delete(Id id);

    void delete(T entity);

    void delete(Iterable<? extends T> entities);

    @SuppressWarnings("unchecked")
    T findOne(Id id);

    T findOne(Specifiable spec);

    @SuppressWarnings("unchecked")
    List<T> findAll();

    List<T> findAll(Specifiable spec);

    <S extends T> S save(S entity);

    @Transactional
    <S extends T> List<S> save(Iterable<S> entities);
}
