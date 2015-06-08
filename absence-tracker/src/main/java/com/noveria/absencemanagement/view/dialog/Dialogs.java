package com.noveria.absencemanagement.view.dialog;

public enum Dialogs {

    ADD_DEPARTMENT("_addDepartmentDialog");

    private final String dialogWidgetVar;

    Dialogs(String dialogWidgetVar) {
        this.dialogWidgetVar = dialogWidgetVar;
    }

    public String getDialogWidgetVar() {
        return dialogWidgetVar;
    }

}
