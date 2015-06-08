package com.noveria.absencemanagement.view.department.controller;

import com.noveria.absencemanagement.service.department.DepartmentService;
import com.noveria.absencemanagement.view.department.model.DepartmentModel;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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

    public void saveDepartment() {
        DepartmentViewBean departmentView = getDepartmentModel().getDepartment();

        logger.debug("Saving Department ("+departmentView.getName()+")");

        departmentService.saveDepartment(departmentView);
    }

    public void clearDepartment() {
        getDepartmentModel().setDepartment(new DepartmentViewBean());
    }

    public DepartmentModel getDepartmentModel() {
        return departmentModel;
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
}
