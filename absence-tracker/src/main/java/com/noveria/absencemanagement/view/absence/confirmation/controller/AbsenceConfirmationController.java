package com.noveria.absencemanagement.view.absence.confirmation.controller;

import com.noveria.absencemanagement.view.absence.confirmation.model.AbsenceConfirmationModel;
import com.noveria.absencemanagement.view.absence.management.model.AbsenceManagementModel;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 19/07/2015.
 */
@ManagedBean(name = "absenceConfirmationController")
@RequestScoped
public class AbsenceConfirmationController {

    @ManagedProperty(value = "#{absenceConfirmationModel}")
    AbsenceConfirmationModel absenceConfirmationModel;

    public void clearAbsense() {
        absenceConfirmationModel.clearAbsence();
    }

    public AbsenceConfirmationModel getAbsenceConfirmationModel() {
        return absenceConfirmationModel;
    }

    public void setAbsenceConfirmationModel(AbsenceConfirmationModel absenceConfirmationModel) {
        this.absenceConfirmationModel = absenceConfirmationModel;
    }

    public List<AbsenceViewBean> getEmployeeAbsencesAwaitingConfirmation() {
        return absenceConfirmationModel.getEmployeeAbsencesAwaitingConfirmation();
    }

    public void confirmAbsence() {
        absenceConfirmationModel.confirmAbsence();
    }

    public List<SelectItem> getAbsenceReasons() {
        List<SelectItem> absenceReasons = new ArrayList<SelectItem>();

        SelectItem reasonOne = new SelectItem("ReasonOne","ReasonOne");
        SelectItem reasonTwo = new SelectItem("ReasonTwo","ReasonTwo");

        absenceReasons.add(reasonOne);
        absenceReasons.add(reasonTwo);

        return absenceReasons;
    }
}
