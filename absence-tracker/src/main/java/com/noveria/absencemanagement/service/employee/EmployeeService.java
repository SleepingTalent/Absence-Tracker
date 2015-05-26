package com.noveria.absencemanagement.service.employee;

import com.noveria.absencemanagement.service.employee.exception.EmployeeNotFoundException;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    public Employee createEmployee(Employee employee) {
        return employeeDAO.create(employee);
    }

    public Employee findEmployee(Long employeeId) throws EmployeeNotFoundException {

        try {
            return employeeDAO.getEmployeeDetails(employeeId);
        } catch (NoResultException e) {
            throw new EmployeeNotFoundException(e);
        }
    }
}
