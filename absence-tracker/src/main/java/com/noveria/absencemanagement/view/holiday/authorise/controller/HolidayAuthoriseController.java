package com.noveria.absencemanagement.view.holiday.authorise.controller;

import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.annualleave.HolidayBreakdown;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.helper.GraphHelper;
import com.noveria.absencemanagement.view.holiday.authorise.model.HolidayAuthoriseModel;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayAuthoriseController")
@RequestScoped
public class HolidayAuthoriseController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayAuthoriseController.class);

    @ManagedProperty(value = "#{holidayAuthoriseModel}")
    HolidayAuthoriseModel holidayAuthoriseModel;

    @ManagedProperty(value = "#{graphHelper}")
    private GraphHelper graphHelper;

    public ScheduleModel getScheduleLazyModel() {
        logger.debug("getScheduleLazyModel : retrieving Schedule");
        return holidayAuthoriseModel.getLazyEventModel();
    }

    public HolidayAuthoriseModel getHolidayAuthoriseModel() {
        return holidayAuthoriseModel;
    }

    public void setHolidayAuthoriseModel(HolidayAuthoriseModel holidayAuthoriseModel) {
        this.holidayAuthoriseModel = holidayAuthoriseModel;
    }

    public GraphHelper getGraphHelper() {
        return graphHelper;
    }

    public void setGraphHelper(GraphHelper graphHelper) {
        this.graphHelper = graphHelper;
    }

    public List<HolidayRequestViewingBean> getPendingHolidayRequests() {
        return getHolidayAuthoriseModel().getPendingHolidayRequests();
    }

    public void approveHoliday() {
        holidayAuthoriseModel.authoriseAnnualLeave();
    }

    public void declineHoliday() {
        holidayAuthoriseModel.declineAnnualLeave();
    }

    public void setSelectedRequest(HolidayRequestViewingBean selected) {
        holidayAuthoriseModel.setSelectedRequest(selected);
    }

    public DonutChartModel getHolidayAllowance() {
        HolidayAllowance departmentHolidayAllowance =
                holidayAuthoriseModel.getDepartmentHolidayAllowance();

        return graphHelper.buildHolidayAllowanceDonutChart(
                "Department Holiday Balance (Hours)", departmentHolidayAllowance.getUsed(),
                departmentHolidayAllowance.getRemaining());
    }

    public BarChartModel getHolidayBarChartStats() {
        HolidayBreakdown holidayBreakdown = holidayAuthoriseModel.getDepartmentHolidayBreakdown();

        return graphHelper.buildHolidayBreakdownBarChart(
                "Department Annual Holiday Breakdown",holidayBreakdown);
    }

    public List<DonutChartModel> getEmployeeHolidayAllowances() {
        List<DonutChartModel> donutChartModels = new ArrayList<DonutChartModel>();

        List<EmployeeViewBean> employeeList = holidayAuthoriseModel.getManagedEmployees();

        for(EmployeeViewBean employee : employeeList) {

            HolidayAllowanceViewBean holidayAllowance =
                    holidayAuthoriseModel.getHolidayAllowanceForEmployee(employee.getId());

            DonutChartModel holidayDonutModel = graphHelper.buildHolidayAllowanceDonutChart(
                    employee.getFullname(), holidayAllowance.getUsed(),
                    holidayAllowance.getRemaining());

            donutChartModels.add(holidayDonutModel);
        }

        return donutChartModels;
    }

    public HolidayRequestViewingBean getSelectedRequest() {
        return holidayAuthoriseModel.getSelectedRequest();
    }
}
