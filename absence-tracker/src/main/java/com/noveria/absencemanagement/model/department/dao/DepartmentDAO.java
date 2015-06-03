package com.noveria.absencemanagement.model.department.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.user.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * This is a data access class that performs actions
 * on the Department Table.
 *
 * This DAO contains methods for creating, updating, and
 * retrieving rows.
 *
 * @author lynseymcgregor
 */

@Repository
public class DepartmentDAO extends BaseDAO<Department> {

    public Department findDepartmentbyName(String name) {
        String sql = "select d from Department d where d.departmentName = :name";

        Query query = entityManager.createQuery(sql);
        query.setParameter("name",name);

        return (Department) query.getSingleResult();
    }

    @Override
    protected Class<Department> getEntityClass() {
        return Department.class;
    }
}
