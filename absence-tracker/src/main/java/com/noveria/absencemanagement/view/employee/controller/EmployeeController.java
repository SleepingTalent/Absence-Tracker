package com.noveria.absencemanagement.view.employee.controller;

/**
 * Created by lynseymcgregor on 14/06/2015.
 */

import com.noveria.absencemanagement.model.common.Role;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.service.administration.AdministrationService;
import com.noveria.absencemanagement.service.employee.EmployeeService;
import com.noveria.absencemanagement.view.authentication.view.UserViewBean;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.model.EmployeeModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AbortProcessingException;
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

    @ManagedProperty(value = "#{administrationService}")
    AdministrationService administrationService;

    @ManagedProperty(value = "#{employeeService}")
    EmployeeService employeeService;

    @ManagedProperty(value = "#{userDAO}")
    UserDAO userDAO;

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;
    private String managerrole;

    public void clearEmployee() {
        employeeModel.setDepartmentId("");
        employeeModel.setUser(new UserViewBean());
        employeeModel.setManagerRole(false);
        employeeModel.setEmployee(new EmployeeViewBean());
    }

    public void saveEmployee() {
       logger.debug("Saving Employee");

       if(userDAO.usernameAlreadyExists(employeeModel.getUser().getUsername())) {
           logger.debug("Create Employee Failed : "+employeeModel.getUser().getUsername()+" already exists");

           messageHelper.addErrorMessage("Create Employee Failed",
                   employeeModel.getUser().getUsername()+" Already Exists");

           throw new AbortProcessingException(employeeModel.getUser().getUsername()+" Already Exists");
       }else {
           administrationService.createEmployee(employeeModel);

           messageHelper.addInfoMessage("Employee Created",
                   employeeModel.getEmployee().getFullname()+" Created Successfully");
       }
    }

    public List<SelectItem> getDepartments() {
        List<SelectItem> departments = new ArrayList<SelectItem>();

        List<DepartmentViewBean> results = administrationService.findAllDepartments();

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

    public UserViewBean getUser() {
        return employeeModel.getUser();
    }

    public AdministrationService getAdministrationService() {
        return administrationService;
    }

    public void setAdministrationService(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public void setManagerrole(boolean hasManagerRole) {
        employeeModel.setManagerRole(hasManagerRole);
    }

    public boolean getManagerrole() {
        return employeeModel.hasManagerRole();
    }
}


