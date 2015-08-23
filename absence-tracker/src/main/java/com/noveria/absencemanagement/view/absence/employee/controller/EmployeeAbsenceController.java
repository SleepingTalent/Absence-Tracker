package com.noveria.absencemanagement.view.absence.employee.controller;

import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.absence.entity.AbsenceReason;
import com.noveria.absencemanagement.view.absence.employee.model.EmployeeAbsenceModel;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import com.noveria.absencemanagement.view.helper.GraphHelper;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 19/07/2015.
 */
@ManagedBean(name = "employeeAbsenceController")
@RequestScoped
public class EmployeeAbsenceController {

    @ManagedProperty(value = "#{employeeAbsenceModel}")
    EmployeeAbsenceModel employeeAbsenceModel;

    @ManagedProperty(value = "#{graphHelper}")
    private GraphHelper graphHelper;

    public void clearAbsense() {
        employeeAbsenceModel.clearAbsence();
    }

    public EmployeeAbsenceModel getEmployeeAbsenceModel() {
        return employeeAbsenceModel;
    }

    public void setEmployeeAbsenceModel(EmployeeAbsenceModel employeeAbsenceModel) {
        this.employeeAbsenceModel = employeeAbsenceModel;
    }

    public GraphHelper getGraphHelper() {
        return graphHelper;
    }

    public void setGraphHelper(GraphHelper graphHelper) {
        this.graphHelper = graphHelper;
    }

    public List<AbsenceViewBean> getEmployeeAbsencesAwaitingConfirmation() {
        return employeeAbsenceModel.getEmployeeAbsencesAwaitingConfirmation();
    }

    public void confirmAbsence() {
        employeeAbsenceModel.confirmAbsence();
    }

    public List<SelectItem> getAbsenceReasons() {
        List<SelectItem> absenceReasons = new ArrayList<SelectItem>();

        for(AbsenceReason absenceReason : AbsenceReason.values()) {
            SelectItem absence = new SelectItem(absenceReason.getDisplayName(), absenceReason.getDisplayName());

            absenceReasons.add(absence);
        }

        return absenceReasons;
    }

    public List<AbsenceViewBean> getEmployeeAbsences() {
        return employeeAbsenceModel.getEmployeeAbsences();
    }

    public BarChartModel getAuthorisedAbsenceStats() {
        List<AbsenceData> absenceDataList =
                employeeAbsenceModel.getAuthorisedAbsenceData();

        return graphHelper.buildAbsenceStats("Authorised Absence",absenceDataList);
    }

    public BarChartModel getUnauthorisedAbsenceStats() {
        List<AbsenceData> absenceDataList =
                employeeAbsenceModel.getUnauthorisedAbsenceData();

        return graphHelper.buildAbsenceStats("Unauthorised Absence",absenceDataList);
    }
}
