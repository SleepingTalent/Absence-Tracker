package com.noveria.absencemanagement.view.dialog;

public enum Dialogs {

    ADD_DEPARTMENT("_addDepartmentDialog"), DELETE_DEPARTMENT("_deleteDepartmentDialog"),
    EDIT_DEPARTMENT("_editDepartmentDialog"), BROWSE_DEPARTMENTS("_browseDepartmentsDialog"),
    ADD_EMPLOYEE("_addEmployeeDialog"), DELETE_EMPLOYEE("_deleteEmployeeDialog"),
    EDIT_EMPLOYEE("_editEmployeeDialog"), BROWSE_EMPLOYEES("_browseEmployeesDialog"),
    ADD_ABSENCE("_addNewAbsenceDialog"), CONFIRM_ABSENCE("_confirmAbsenceDialog"),
    SELECT_EMPLOYEE("_selectEmployeeDialog"), EMPLOYEE_ABSENCE("_employeeAbsenceDialog"),
    REQUEST_HOLIDAY("_requestNewHolidayDialog"), EMPLOYEE_ABSENCE_STATS("_employeeAbsenceStatsDialog"),
    EMPLOYEE_HOLIDAY_STATS("_employeeHolidayStatsDialog");

    private final String dialogWidgetVar;

    Dialogs(String dialogWidgetVar) {
        this.dialogWidgetVar = dialogWidgetVar;
    }

    public String getDialogWidgetVar() {
        return dialogWidgetVar;
    }

}
