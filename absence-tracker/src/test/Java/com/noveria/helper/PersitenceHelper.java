package com.noveria.helper;

import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersitenceHelper {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    UserDAO userDAO;

    public Employee persistNewEmployee(Employee employee) {

        Assert.assertNull("Expected Id to be Null!",employee.getId());

        employee = employeeDAO.create(employee);

        Assert.assertNotNull("Expected Id to be populated!",employee.getId());

        return employee;
    }

    public User persistNewUser(User user) {
        //Assert.assertNull("Expected Id to be Null!",employee.getId());

        user = userDAO.create(user);

        //Assert.assertNotNull("Expected Id to be populated!",employee.getId());

        return user;
    }
}
