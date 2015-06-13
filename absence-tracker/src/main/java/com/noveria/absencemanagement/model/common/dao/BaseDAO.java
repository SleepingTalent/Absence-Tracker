package com.noveria.absencemanagement.model.common.dao;

import com.noveria.absencemanagement.model.employee.dao.BrowseEmployeePagenatedResults;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public abstract class BaseDAO<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

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

    public PagenatedResults findPagenatedResults(int first, int pageSize, PagenatedResults results, String selectSql, String countSql) {

        Query query = entityManager.createQuery(selectSql);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        List<T> foundEmployees = query.getResultList();

        logger.debug("Paginated Results Count ("+foundEmployees.size()+")");

        results.setResultsList(foundEmployees);

        Query countQuery = entityManager.createQuery(countSql);

        long totalCount = (Long)countQuery.getSingleResult();

        logger.debug("Total Count ("+totalCount+")");

        results.setTotalCount((int)totalCount);

        return results;
    }
}

