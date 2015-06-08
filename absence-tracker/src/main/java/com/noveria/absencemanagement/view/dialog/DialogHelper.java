package com.noveria.absencemanagement.view.dialog;

import org.primefaces.context.RequestContext;

public class DialogHelper {

    public void hideDialog(Dialogs dialog) {
        RequestContext.getCurrentInstance().execute("PF('"+dialog.getDialogWidgetVar() + "').hide()");
    }

    public void showDialog(Dialogs dialog) {
        RequestContext.getCurrentInstance().execute("PF('"+dialog.getDialogWidgetVar() + "').show()");
    }
}
