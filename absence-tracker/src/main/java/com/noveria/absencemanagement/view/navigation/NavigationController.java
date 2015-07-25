package com.noveria.absencemanagement.view.navigation;

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

    public String showAdminFeatures() {
        return NavigationOutcome.SUCCESS.getOutcomeName();
    }

    public String showHolidayManagementFeatures() {
       return NavigationOutcome.SUCCESS.getOutcomeName();
    }

    public String showHolidayAuthoriseFeatures() {
        return NavigationOutcome.SUCCESS.getOutcomeName();
    }

    public String showEnterAbsenceFeatures() {
        return NavigationOutcome.SUCCESS.getOutcomeName();
    }

    public String showConfirmAbsenceFeatures() {
        return NavigationOutcome.SUCCESS.getOutcomeName();
    }

    public void showNewAbsenceDialog() {
        viewHelper.showNewAbsenceDialog();
    }

    public void hideNewAbsenceDialog() {
        viewHelper.hideNewAbsenceDialog();
    }

    public void showConfirmAbsenceDialog() {
        viewHelper.showConfirmAbsenceDialog();
    }

    public void hideConfirmAbsenceDialog() {
        viewHelper.hideConfirmAbsenceDialog();
    }
}