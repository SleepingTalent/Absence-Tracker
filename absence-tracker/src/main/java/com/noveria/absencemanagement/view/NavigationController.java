package com.noveria.absencemanagement.view;

import com.noveria.absencemanagement.view.helper.ViewHelper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController")
@RequestScoped
public class NavigationController {

    ViewHelper viewHelper;

    @PostConstruct
    public void postContruct() {
        viewHelper = new ViewHelper();
    }

    public void showAddDepartmentDialog() {
        viewHelper.showAddDepartmentDialog();
    }

    public void showEditDepartmentDialog() {
        viewHelper.showEditDepartmentDialog();
    }

    public void showDeleteDepartmentDialog() {
        viewHelper.showDeleteDepartmentDialog();
    }

    public void showBrowseDepartmentsDialog() {
        viewHelper.showBrowseDepartmentsDialog();
    }

    public void hideAddDepartmentDialog() {
        viewHelper.hideAddDepartmentDialog();
    }

    public void showBrowseEmployeesDialog() {viewHelper.showBrowseEmployeeDialog();}

    public void showEditEmployeeDialog() {
        viewHelper.showEditEmployeeDialog();
    }

    public void showDeleteEmployeeDialog() {
        viewHelper.showDeleteEmployeeDialog();
    }

    public void showAddEmployeeDialog() {
        viewHelper.showAddEmployeeDialog();
    }

    public void hideAddEmployeeDialog() {
        viewHelper.hideAddEmployeeDialog();
    }

}