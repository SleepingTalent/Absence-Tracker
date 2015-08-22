package com.noveria.absencemanagement.view.absence.employee.controller;

import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.absence.entity.AbsenceReason;
import com.noveria.absencemanagement.view.absence.employee.model.EmployeeAbsenceModel;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
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

    public void clearAbsense() {
        employeeAbsenceModel.clearAbsence();
    }

    public EmployeeAbsenceModel getEmployeeAbsenceModel() {
        return employeeAbsenceModel;
    }

    public void setEmployeeAbsenceModel(EmployeeAbsenceModel employeeAbsenceModel) {
        this.employeeAbsenceModel = employeeAbsenceModel;
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
        BarChartModel authorisedAbsenceStats = new BarChartModel();

        ChartSeries absence = new ChartSeries();

        List<AbsenceData> absenceDataList = employeeAbsenceModel.getAuthorisedAbsenceData();

        for(AbsenceData absenceData : absenceDataList) {
            absence.set(AbsenceReason.findByName(
                    absenceData.getAbsenceType()).getDisplayName(),
                    absenceData.getTotal());
        }

        authorisedAbsenceStats.addSeries(absence);
        authorisedAbsenceStats.setTitle("Authorised Absence");
        authorisedAbsenceStats.setExtender("chartExtender");

        Axis yAxis = authorisedAbsenceStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);
        yAxis.setMax(100);

        return authorisedAbsenceStats;
    }

    public BarChartModel getUnauthorisedAbsenceStats() {
        BarChartModel unAuthorisedAbsenceStats = new BarChartModel();

        ChartSeries absence = new ChartSeries();

        List<AbsenceData> absenceDataList = employeeAbsenceModel.getUnauthorisedAbsenceData();

        for(AbsenceData absenceData : absenceDataList) {
            absence.set(AbsenceReason.findByName(
                            absenceData.getAbsenceType()).getDisplayName(),
                    absenceData.getTotal());
        }

        unAuthorisedAbsenceStats.addSeries(absence);
        unAuthorisedAbsenceStats.setTitle("Unauthorised Absence");
        unAuthorisedAbsenceStats.setExtender("chartExtender");

        Axis yAxis = unAuthorisedAbsenceStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);
        yAxis.setMax(100);

        return unAuthorisedAbsenceStats;
    }
}
