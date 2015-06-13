package com.noveria.absencemanagement.view.department.controller;

import com.noveria.absencemanagement.service.department.DepartmentService;
import com.noveria.absencemanagement.view.department.model.DepartmentModel;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AbortProcessingException;

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

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;

    public void saveDepartment() {
        DepartmentViewBean departmentView = getDepartmentModel().getDepartment();

        if(departmentService.departmentAlreadyExists(departmentView.getName())) {
            logger.debug("Create Department Failed : "+departmentView.getName()+" already exists");

            messageHelper.addErrorMessage("Create Department Failed",
                    departmentView.getName()+" Already Exists");

            throw new AbortProcessingException(departmentView.getName()+" Already Exists");
        } else {
            logger.debug("Saving Department ("+departmentView.getName()+")");

            departmentService.saveDepartment(departmentView);
        }
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

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
