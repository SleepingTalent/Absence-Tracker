package com.noveria.absencemanagement.view.employee.controller;

/**
 * Created by lynseymcgregor on 14/06/2015.
 */

import com.noveria.absencemanagement.model.common.Role;
import com.noveria.absencemanagement.service.department.DepartmentService;
import com.noveria.absencemanagement.service.employee.EmployeeService;
import com.noveria.absencemanagement.view.authentication.view.UserViewBean;
import com.noveria.absencemanagement.view.department.model.DepartmentModel;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.model.EmployeeModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 08/06/2015.
 */
@ManagedBean(name = "employeeController")
@RequestScoped
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @ManagedProperty(value = "#{employeeModel}")
    EmployeeModel employeeModel;

    @ManagedProperty(value = "#{departmentService}")
    DepartmentService departmentService;

    @ManagedProperty(value = "#{employeeService}")
    EmployeeService employeeService;

    public void clearEmployee() {
        employeeModel.setDepartmentId("");
        employeeModel.setUser(new UserViewBean());
        employeeModel.setRole("");
        employeeModel.setEmployee(new EmployeeViewBean());
    }

    public void saveEmployee() {
       logger.debug("Saving Employee");
       employeeService.createEmployee(employeeModel);
    }

    public List<SelectItem> getDepartments() {
        List<SelectItem> departments = new ArrayList<SelectItem>();

        List<DepartmentViewBean> results = departmentService.findAllDepartments();

        for(DepartmentViewBean departmentResult : results) {
            SelectItem department = new SelectItem(departmentResult.getId(),departmentResult.getName());
            departments.add(department);
        }

        return departments;
    }

    public List<SelectItem> getRoles() {
        List<SelectItem> roles = new ArrayList<SelectItem>();

        SelectItem managerRole = new SelectItem(Role.MANAGER.getId(), Role.MANAGER.getName());
        SelectItem employeeRole = new SelectItem(Role.EMPLOYEE.getId(), Role.EMPLOYEE.getName());

        roles.add(managerRole);
        roles.add(employeeRole) ;

        return roles;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public EmployeeViewBean getEmployee() {
        return employeeModel.getEmployee();
    }

    public void setDepartmentId(String departmentId) {
        this.employeeModel.setDepartmentId(departmentId);
    }

    public String getDepartmentId() {
        return employeeModel.getDepartmentId();
    }

    public void setRole(String role) {
        this.employeeModel.setRole(role);
    }

    public String getRole() {
        return employeeModel.getRole();
    }

    public UserViewBean getUser() {
        return employeeModel.getUser();
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}

