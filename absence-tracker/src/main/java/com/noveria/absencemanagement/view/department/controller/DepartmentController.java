package com.noveria.absencemanagement.view.department.controller;

import com.noveria.absencemanagement.service.department.DepartmentService;
import com.noveria.absencemanagement.service.employee.EmployeeService;
import com.noveria.absencemanagement.view.department.model.DepartmentModel;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
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
@ManagedBean(name = "departmentController")
@RequestScoped
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @ManagedProperty(value = "#{departmentModel}")
    DepartmentModel departmentModel;

    @ManagedProperty(value = "#{departmentService}")
    DepartmentService departmentService;

    @ManagedProperty(value = "#{employeeService}")
    EmployeeService employeeService;

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;

    private String managerId;

    public void saveDepartment() {
        DepartmentViewBean departmentView = getDepartmentModel().getDepartment();

        if(departmentService.departmentAlreadyExists(departmentView.getName())) {
            logger.debug("Create Department Failed : "+departmentView.getName()+" already exists");

            messageHelper.addErrorMessage("Create Department Failed",
                    departmentView.getName()+" Already Exists");

            throw new AbortProcessingException(departmentView.getName()+" Already Exists");
        } else {
            logger.debug("Saving Department ("+departmentView.getName()+")");

            if(managerId != null) {
                logger.debug("Setting Manager ("+managerId+")");
                EmployeeViewBean manager = new EmployeeViewBean();
                manager.setId(new Long(managerId));
                departmentView.setManager(manager);
            }

            departmentService.saveDepartment(departmentView);

            messageHelper.addInfoMessage("Department Created",
                    departmentView.getName()+" Created Successfully");
        }
    }

    public void clearDepartment() {
        getDepartmentModel().setDepartment(new DepartmentViewBean());
    }

    public DepartmentModel getDepartmentModel() {
        return departmentModel;
    }

    public List<SelectItem> getManagers() {
        List<SelectItem> managers = new ArrayList<SelectItem>();

        for(EmployeeViewBean employeeViewBean : employeeService.findAllManagers()) {
            SelectItem manager = new SelectItem(employeeViewBean.getId().toString(),employeeViewBean.getFullname());
            managers.add(manager);
        }

        return managers;
    }

    public DepartmentViewBean getDepartment() {
        return getDepartmentModel().getDepartment();
    }

    public void setDepartmentModel(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
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

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public void setManagerId(String managerId) {

        this.managerId = managerId;
    }

    public String getManagerId() {

        return managerId;
    }
}
