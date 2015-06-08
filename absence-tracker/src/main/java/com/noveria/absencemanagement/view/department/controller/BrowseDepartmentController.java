package com.noveria.absencemanagement.view.department.controller;

import com.noveria.absencemanagement.view.department.model.BrowserDepartmentModel;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.primefaces.model.LazyDataModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "browseDepartmentController")
@RequestScoped
public class BrowseDepartmentController {

    @ManagedProperty(value = "#{browserDepartmentModel}")
    BrowserDepartmentModel browserDepartmentModel;

    @ManagedProperty(value = "#{departmentController}")
    DepartmentController departmentController;

    DepartmentViewBean departmentViewBean;

    public DepartmentViewBean getSelectedDepartment() {
        return departmentViewBean;
    }

    public void setDepartmentEmployee(DepartmentViewBean departmentViewBean) {
        this.departmentViewBean = departmentViewBean;
    }

    public LazyDataModel getLazyModel() {
        return browserDepartmentModel.getDataModel();
    }

    public void clearDataModel() {
        browserDepartmentModel.clearDataModel();
    }

    public void loadDepartment() {
        DepartmentViewBean selectedDepartment = getSelectedDepartment();
        departmentController.getDepartmentModel().setDepartment(selectedDepartment);
    }

    public DepartmentController getDepartmentController() {
        return departmentController;
    }

    public void setDepartmentController(DepartmentController departmentController) {
        this.departmentController = departmentController;
    }

    public BrowserDepartmentModel getBrowserDepartmentModel() {
        return browserDepartmentModel;
    }

    public void setBrowserDepartmentModel(BrowserDepartmentModel browserDepartmentModel) {
        this.browserDepartmentModel = browserDepartmentModel;
    }
}
