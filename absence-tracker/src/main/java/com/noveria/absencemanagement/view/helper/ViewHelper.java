package com.noveria.absencemanagement.view.helper;


import com.noveria.absencemanagement.view.dialog.DialogHelper;
import com.noveria.absencemanagement.view.dialog.Dialogs;

import javax.faces.context.FacesContext;

public class ViewHelper {

    DialogHelper dialogHelper;

    public ViewHelper() {
        dialogHelper = new DialogHelper();
    }

    public void failValidation() {
        FacesContext.getCurrentInstance().validationFailed();
    }

    public void showAddDepartmentDialog() {
        dialogHelper.showDialog(Dialogs.ADD_DEPARTMENT);
    }

    public void hideAddDepartmentDialog() {
        dialogHelper.hideDialog(Dialogs.ADD_DEPARTMENT);
    }

    public void showDeleteDepartmentDialog() {
        dialogHelper.showDialog(Dialogs.DELETE_DEPARTMENT);
    }

    public void showEditDepartmentDialog() {
        dialogHelper.showDialog(Dialogs.EDIT_DEPARTMENT);
    }

    public void showBrowseDepartmentsDialog() {
        dialogHelper.showDialog(Dialogs.BROWSE_DEPARTMENTS);
    }

    public void showAddEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.ADD_EMPLOYEE);
    }

    public void hideAddEmployeeDialog() {
        dialogHelper.hideDialog(Dialogs.ADD_EMPLOYEE);
    }

    public void showBrowseEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.BROWSE_EMPLOYEES);
    }

    public void showEditEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.EDIT_EMPLOYEE);
    }

    public void showDeleteEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.DELETE_EMPLOYEE);
    }

    public void showNewAbsenceDialog() {
        dialogHelper.showDialog(Dialogs.ADD_ABSENCE);
    }

    public void hideNewAbsenceDialog() {
        dialogHelper.hideDialog(Dialogs.ADD_ABSENCE);
    }

    public void showConfirmAbsenceDialog() {
        dialogHelper.showDialog(Dialogs.CONFIRM_ABSENCE);
    }

    public void hideConfirmAbsenceDialog() {
        dialogHelper.hideDialog(Dialogs.CONFIRM_ABSENCE);
    }

    public void showSelectEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.SELECT_EMPLOYEE);
    }

    public void hideSelectEmployeeDialog() {
        dialogHelper.hideDialog(Dialogs.SELECT_EMPLOYEE);
    }

    public void showEmployeeAbsenceDialog() {
        dialogHelper.showDialog(Dialogs.EMPLOYEE_ABSENCE);
    }

    public void showRequestHolidayDialog() {
        dialogHelper.showDialog(Dialogs.REQUEST_HOLIDAY);
    }

    public void hideRequestHolidayDialog() {
        dialogHelper.hideDialog(Dialogs.REQUEST_HOLIDAY);
    }

    public void showEmployeeAbsenceStatsDialog() {
        dialogHelper.showDialog(Dialogs.EMPLOYEE_ABSENCE_STATS);
    }

    public void hideEmployeeAbsenceStatsDialog() {
        dialogHelper.hideDialog(Dialogs.EMPLOYEE_ABSENCE_STATS);
    }

    public void showEmployeeHolidayStatsDialog() {
        dialogHelper.showDialog(Dialogs.EMPLOYEE_HOLIDAY_STATS);
    }

    public void hideEmployeeHolidayStatsDialog() {
        dialogHelper.hideDialog(Dialogs.EMPLOYEE_HOLIDAY_STATS);
    }

    public void showDepartmentAbsenceStatsDialog() {
        dialogHelper.showDialog(Dialogs.DEPARTMENT_ABSENCE_STATS);
    }

    public void hideDepartmentAbsenceStatsDialog() {
        dialogHelper.hideDialog(Dialogs.DEPARTMENT_ABSENCE_STATS);
    }

    public void showDepartmentHolidayStatsDialog() {
        dialogHelper.showDialog(Dialogs.DEPARTMENT_HOLIDAY_STATS);
    }

    public void hideDepartmentHolidayStatsDialog() {
        dialogHelper.hideDialog(Dialogs.DEPARTMENT_HOLIDAY_STATS);
    }
}
