package com.noveria.absencemanagement.model.department.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.common.dao.PagenatedResults;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    private static final Logger logger = LoggerFactory.getLogger(DepartmentDAO.class);

    public Department findDepartmentbyName(String name) {
        String sql = "select d from Department d where d.departmentName = :name";

        Query query = entityManager.createQuery(sql);
        query.setParameter("name",name);

        return (Department) query.getSingleResult();
    }

    public List<Department> findDepartmentbyManager(Employee manager) {
        String sql = "select d from Department d where d.manager = :manager";

        Query query = entityManager.createQuery(sql);
        query.setParameter("manager",manager);

        return query.getResultList();
    }

    public BrowseDepartmentPagenatedResults findAllDepartmentsPagenated(int first, int pageSize) {
        BrowseDepartmentPagenatedResults results = new BrowseDepartmentPagenatedResults();

        String selectSql = "select d from Department d";
        String countSql = "select count(d) from Department d";

        return (BrowseDepartmentPagenatedResults) findPagenatedResults(first, pageSize, results, selectSql, countSql);
    }



    @Override
    protected Class<Department> getEntityClass() {
        return Department.class;
    }

    public List<Department> findAllDepartments() {
        String sql = "select d from Department d";
        Query query = entityManager.createQuery(sql);

        return query.getResultList();
    }
}
