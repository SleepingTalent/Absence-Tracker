package com.noveria.helper;

import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.dao.UserRoleDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersistenceHelper {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserRoleDAO userRoleDAO;

    public Employee persistNewEmployee(Employee employee) {

        Assert.assertNull("Expected Id to be Null!",employee.getId());

        employee = employeeDAO.create(employee);

        Assert.assertNotNull("Expected Id to be populated!",employee.getId());

        return employee;
    }

    public User persistNewUser(User user) {
        Assert.assertNull("Expected Id to be Null!", user.getId());

        user = userDAO.create(user);

        Assert.assertNotNull("Expected Id to be populated!", user.getId());

        return user;
    }

    public UserRole persistNewUserRole(UserRole role) {
        Assert.assertNull("Expected Id to be Null!", role.getId());

        role = userRoleDAO.create(role);

        Assert.assertNotNull("Expected Id to be populated!", role.getId());

        return role;

    }
}
