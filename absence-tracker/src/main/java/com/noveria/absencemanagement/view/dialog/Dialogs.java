package com.noveria.absencemanagement.view.dialog;

public enum Dialogs {

    ADD_DEPARTMENT("_addDepartmentDialog"), DELETE_DEPARTMENT("_deleteDepartmentDialog"),
    EDIT_DEPARTMENT("_editDepartmentDialog"), BROWSE_DEPARTMENTS("_browseDepartmentsDialog");

    private final String dialogWidgetVar;

    Dialogs(String dialogWidgetVar) {
        this.dialogWidgetVar = dialogWidgetVar;
    }

    public String getDialogWidgetVar() {
        return dialogWidgetVar;
    }

}
