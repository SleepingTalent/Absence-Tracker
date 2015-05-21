package com.noveria.absencemanagement.model.employee.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class EmployeeDAO extends BaseDAO<Employee> {

    public Employee getEmployeeDetails(Long employeeId) throws NoResultException {
        return findById(employeeId);
    }

    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
