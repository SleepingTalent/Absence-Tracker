package com.noveria.absencemanagement.model.common.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class BaseDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    public long countAll() {
        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(getEntityClass().getSimpleName()).append(" o ");

        final Query query = entityManager.createQuery(queryString.toString());

        return (Long) query.getSingleResult();
    }

    public T create(final T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    public void delete(final T entity) {
        entityManager.remove(entityManager.merge(entity));
        entityManager.flush();
    }

    public T findById(final Object id) {
        T result = (T) entityManager.find(getEntityClass(), id);

        if (result == null) {
            throw new NoResultException("No Results found for " +
                    getEntityClass().getName() + " with id " + id);
        }

        return result;
    }

    public T update(final T entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }
}

