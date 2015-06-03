package com.noveria.absencemanagement.model.employee.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAO extends BaseDAO<Employee> {

    public Employee getEmployeeDetails(Long employeeId) throws NoResultException {
        return findById(employeeId);
    }

    public List<Employee> findEmployeesbyDepartmentId(Department department) {
        String sql = "select e from Employee e where e.department = :department";

        Query query = entityManager.createQuery(sql);
        query.setParameter("department",department);

        return query.getResultList();
    }

    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
