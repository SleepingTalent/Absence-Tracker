package com.noveria.absencemanagement.view.employee.model;

import com.noveria.absencemanagement.model.common.Role;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import com.noveria.absencemanagement.view.authentication.view.UserViewBean;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import org.springframework.stereotype.Component;

import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 14/06/2015.
 */
@Component
@SessionScoped
public class EmployeeModel {

    private EmployeeViewBean employee;
    private UserViewBean user;
    private String role;
    private String departmentId;

    public EmployeeViewBean getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeViewBean employee) {
        this.employee = employee;
    }

    public UserViewBean getUser() {
        return user;
    }

    public void setUser(UserViewBean user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public User getUserEntity() {
        User userEntity = new User();

        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEnabled(true);

        UserRole userRole = new UserRole();
        userRole.setRole(Role.findById(role).getName());

        List<UserRole> roles = new ArrayList<UserRole>();
        roles.add(userRole);

        userEntity.setUserRole(roles);

        return userEntity;
    }

    public Employee getEmployeeEntity() {
        Employee employeeEntity = new Employee();
        employeeEntity.setFirstName(employee.getFirstname());
        employeeEntity.setLastName(employee.getLastname());
        return employeeEntity;
    }
}
