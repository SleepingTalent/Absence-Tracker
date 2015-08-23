package com.noveria.absencemanagement.view.absence.management.controller;

import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.absence.entity.AbsenceReason;
import com.noveria.absencemanagement.view.absence.management.model.AbsenceManagementModel;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceStatsViewBean;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.helper.GraphHelper;
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

    @ManagedProperty(value = "#{graphHelper}")
    private GraphHelper graphHelper;

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

    public GraphHelper getGraphHelper() {
        return graphHelper;
    }

    public void setGraphHelper(GraphHelper graphHelper) {
        this.graphHelper = graphHelper;
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
        List<AbsenceData> absenceDataList =
                absenceManagementModel.getAuthorisedAbsenceData();

        return graphHelper.buildAbsenceStats(
                "Department Authorised Absence",absenceDataList);
    }

    public BarChartModel getUnauthorisedAbsenceStats() {
        List<AbsenceData> absenceDataList = absenceManagementModel.getUnauthorisedAbsenceData();

        return graphHelper.buildAbsenceStats(
                "Department Unauthorised Absence",absenceDataList);
    }

    public List<AbsenceStatsViewBean> getEmployeeAbsenceStats() {
        List<AbsenceStatsViewBean> absenceStatsViewBeanList = new ArrayList<AbsenceStatsViewBean>();

        List<EmployeeViewBean> employeeList = absenceManagementModel.getManagedEmployees();

        int index = 0;

        for(EmployeeViewBean employee : employeeList) {
            AbsenceStatsViewBean absenceStatsViewBean = new AbsenceStatsViewBean(index);

            BarChartModel authorisedAbsenceStats = new BarChartModel();
            ChartSeries absence = new ChartSeries();

            List<AbsenceData> absenceDataList = absenceManagementModel.getAuthorisedAbsenceDataForEmployee(employee.getId());

            for(AbsenceData absenceData : absenceDataList) {
                absence.set(AbsenceReason.findByName(
                                absenceData.getAbsenceType()).getDisplayName(),
                        absenceData.getTotal());
            }

            authorisedAbsenceStats.addSeries(absence);
            authorisedAbsenceStats.setTitle(employee.getFullname()+" Authorised Absence");
            authorisedAbsenceStats.setExtender("chartExtender");

            Axis yAxis = authorisedAbsenceStats.getAxis(AxisType.Y);
            yAxis.setLabel("Days");
            yAxis.setMin(0);

            absenceStatsViewBean.setAuthorisedAbsence(authorisedAbsenceStats);

            BarChartModel unAuthorisedAbsenceStats = new BarChartModel();

            absence = new ChartSeries();

            absenceDataList = absenceManagementModel.getUnauthorisedAbsenceDataForEmployee(employee.getId());

            for(AbsenceData absenceData : absenceDataList) {
                absence.set(AbsenceReason.findByName(
                                absenceData.getAbsenceType()).getDisplayName(),
                        absenceData.getTotal());
            }

            unAuthorisedAbsenceStats.addSeries(absence);
            unAuthorisedAbsenceStats.setTitle(employee.getFullname()+" Unauthorised Absence");
            unAuthorisedAbsenceStats.setExtender("chartExtender");

            yAxis = unAuthorisedAbsenceStats.getAxis(AxisType.Y);
            yAxis.setLabel("Days");
            yAxis.setMin(0);
            yAxis.setMax(100);

            absenceStatsViewBean.setUnAuthorisedAbsence(unAuthorisedAbsenceStats);

            absenceStatsViewBeanList.add(absenceStatsViewBean);

            index++;
        }

        return absenceStatsViewBeanList;
    }
}
