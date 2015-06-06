package com.noveria.absencemanagement.service.employee;

import com.noveria.absencemanagement.service.employee.exception.EmployeeNotFoundException;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeDAO employeeDAO;

    public Employee createEmployee(Employee employee) {
        logger.debug("Creating Employee : "+employee.getFirstName()+" "+employee.getLastName());
        return employeeDAO.create(employee);
    }

    public Employee findEmployee(Long employeeId) throws EmployeeNotFoundException {

        try {
            logger.debug("Searching for Employee By Id: "+employeeId);
            return employeeDAO.getEmployeeDetails(employeeId);
        } catch (NoResultException e) {
            logger.warn("No Employee Found By Id: "+employeeId);
            throw new EmployeeNotFoundException(e);
        }
    }
}
