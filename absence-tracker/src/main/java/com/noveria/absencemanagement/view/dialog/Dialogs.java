package com.noveria.absencemanagement.view.dialog;

public enum Dialogs {

    ADD_DEPARTMENT("_addDepartmentDialog"), DELETE_DEPARTMENT("_deleteDepartmentDialog"),
    EDIT_DEPARTMENT("_editDepartmentDialog"), BROWSE_DEPARTMENTS("_browseDepartmentsDialog"),
    ADD_EMPLOYEE("_addEmployeeDialog"), DELETE_EMPLOYEE("_deleteEmployeeDialog"), EDIT_EMPLOYEE("_editEmployeeDialog"),
    BROWSE_EMPLOYEES("_browseEmployeesDialog"), ADD_ABSENCE("_addNewAbsenceDialog");

    private final String dialogWidgetVar;

    Dialogs(String dialogWidgetVar) {
        this.dialogWidgetVar = dialogWidgetVar;
    }

    public String getDialogWidgetVar() {
        return dialogWidgetVar;
    }

}
