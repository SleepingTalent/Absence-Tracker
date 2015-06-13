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

    public void showBrowseEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.BROWSE_EMPLOYEES);
    }

    public void showEditEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.EDIT_EMPLOYEE);

    }

    public void showDeleteEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.DELETE_EMPLOYEE);
    }
}
