package com.noveria.helper;

import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
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
    DepartmentDAO departmentDAO;

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

    public Department persistNewDepartment(Department department) {
        Assert.assertNull("Expected Id to be Null!", department.getId());

        department = departmentDAO.create(department);

        Assert.assertNotNull("Expected Id to be populated!", department.getId());

        return department;
    }

    public void deleteDepartment(Department department) {
        departmentDAO.delete(department);
    }

    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee);
    }
}
