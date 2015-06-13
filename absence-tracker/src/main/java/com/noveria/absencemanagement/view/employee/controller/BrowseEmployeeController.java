package com.noveria.absencemanagement.view.employee.controller;

import com.noveria.absencemanagement.view.employee.model.BrowseEmployeeModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import org.primefaces.model.LazyDataModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by lynseymcgregor on 13/06/2015.
 */

@ManagedBean(name = "browseEmployeeController")
@RequestScoped
public class BrowseEmployeeController {

    @ManagedProperty(value = "#{browseEmployeeModel}")
    BrowseEmployeeModel browseEmployeeModel;

    EmployeeViewBean employeeViewBean;

    public BrowseEmployeeModel getBrowseEmployeeModel() {
        return browseEmployeeModel;
    }

    public void setBrowseEmployeeModel(BrowseEmployeeModel browseEmployeeModel) {
        this.browseEmployeeModel = browseEmployeeModel;
    }

    public EmployeeViewBean getEmployeeViewBean() {
        return employeeViewBean;
    }

    public void setEmployeeViewBean(EmployeeViewBean employeeViewBean) {
        this.employeeViewBean = employeeViewBean;
    }

    public LazyDataModel getLazyModel() {
        return browseEmployeeModel.getDataModel();
    }

    public void clearDataModel() {
        browseEmployeeModel.clearDataModel();
    }

    public void loadEmployee() {

    }
}
