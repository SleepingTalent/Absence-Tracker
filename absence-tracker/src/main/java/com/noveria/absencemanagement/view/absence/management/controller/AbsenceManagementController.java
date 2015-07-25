package com.noveria.absencemanagement.view.absence.management.controller;

import com.noveria.absencemanagement.view.absence.management.model.AbsenceManagementModel;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.holiday.management.model.HolidayManagementModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 19/07/2015.
 */
@ManagedBean(name = "absenceManagementController")
@RequestScoped
public class AbsenceManagementController {

    @ManagedProperty(value = "#{absenceManagementModel}")
    AbsenceManagementModel absenceManagementModel;

    public void clearAbsense() {
        absenceManagementModel.clearAbsence();
    }

    public void clearEmployeeAbsense() {
        absenceManagementModel.clearEmployeeAbsence();
    }

    public AbsenceManagementModel getAbsenceManagementModel() {
        return absenceManagementModel;
    }

    public void setAbsenceManagementModel(AbsenceManagementModel absenceManagementModel) {
        this.absenceManagementModel = absenceManagementModel;
    }

    public void createAbsence() {
        absenceManagementModel.createAbsence();
    }

    public List<SelectItem> getManagedEmployees() {
            List<SelectItem> managers = new ArrayList<SelectItem>();

            for(EmployeeViewBean employeeViewBean : absenceManagementModel.getManagedEmployees()) {
                SelectItem manager = new SelectItem(employeeViewBean.getId().toString(),employeeViewBean.getFullname());
                managers.add(manager);
            }

            return managers;
     }

    public List<AbsenceViewBean> getEmployeeAbsencesAwaitingConfirmationByManager() {
        return absenceManagementModel.findAllAbsenceAwaitingConfirmationByManager();
    }


    public List<AbsenceViewBean> getEmployeeAbsences() {
        return absenceManagementModel.getEmployeeAbsences();
    }
}
