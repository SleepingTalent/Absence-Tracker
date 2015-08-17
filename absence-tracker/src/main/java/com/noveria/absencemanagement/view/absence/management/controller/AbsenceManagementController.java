package com.noveria.absencemanagement.view.absence.management.controller;

import com.noveria.absencemanagement.view.absence.management.model.AbsenceManagementModel;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceStatsViewBean;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.holiday.management.model.HolidayManagementModel;
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

    public BarChartModel getAuthorisedAbsenceStats() {
        BarChartModel authorisedAbsenceStats = new BarChartModel();

        ChartSeries absence = new ChartSeries();

        absence.set("Maternity", 90);
        absence.set("Paternity", 10);
        absence.set("Carer's", 20);
        absence.set("Bereavement", 100);

        authorisedAbsenceStats.addSeries(absence);
        authorisedAbsenceStats.setTitle("Department Authorised Absence");
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

        absence.set("Cold/Flu", 90);
        absence.set("Stress/Depression", 10);
        absence.set("Back Problems", 20);
        absence.set("Gastro-intestinal", 100);
        absence.set("Dental/Oral", 20);

        unAuthorisedAbsenceStats.addSeries(absence);
        unAuthorisedAbsenceStats.setTitle("Department Unauthorised Absence");
        unAuthorisedAbsenceStats.setExtender("chartExtender");

        Axis yAxis = unAuthorisedAbsenceStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);
        yAxis.setMax(100);

        return unAuthorisedAbsenceStats;
    }

    public List<AbsenceStatsViewBean> getEmployeeAbsenceStats() {
        List<AbsenceStatsViewBean> absenceStatsViewBeanList = new ArrayList<AbsenceStatsViewBean>();
        AbsenceStatsViewBean absenceStatsViewBean = new AbsenceStatsViewBean(0);

        BarChartModel authorisedAbsenceStats = new BarChartModel();

        ChartSeries absence = new ChartSeries();

        absence.set("Maternity", 90);
        absence.set("Paternity", 10);
        absence.set("Carer's", 20);
        absence.set("Bereavement", 100);

        authorisedAbsenceStats.addSeries(absence);
        authorisedAbsenceStats.setTitle("Jane Worker Authorised Absence");
        authorisedAbsenceStats.setExtender("chartExtender");

        Axis yAxis = authorisedAbsenceStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);

        absenceStatsViewBean.setAuthorisedAbsence(authorisedAbsenceStats);

        BarChartModel unAuthorisedAbsenceStats = new BarChartModel();

        absence = new ChartSeries();

        absence.set("Cold/Flu", 90);
        absence.set("Stress/Depression", 10);
        absence.set("Back Problems", 20);
        absence.set("Gastro-intestinal", 100);
        absence.set("Dental/Oral", 20);

        unAuthorisedAbsenceStats.addSeries(absence);
        unAuthorisedAbsenceStats.setTitle("Jane Worker Unauthorised Absence");
        unAuthorisedAbsenceStats.setExtender("chartExtender");

        yAxis = unAuthorisedAbsenceStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);
        yAxis.setMax(100);

        absenceStatsViewBean.setUnAuthorisedAbsence(unAuthorisedAbsenceStats);

        absenceStatsViewBeanList.add(absenceStatsViewBean);

        return absenceStatsViewBeanList;
    }
}
