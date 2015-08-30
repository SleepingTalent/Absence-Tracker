package com.noveria.absencemanagement.view.holiday.management.controller;

import com.noveria.absencemanagement.model.holiday.annualleave.HolidayBreakdown;
import com.noveria.absencemanagement.model.holiday.annualleave.Month;
import com.noveria.absencemanagement.view.helper.GraphHelper;
import com.noveria.absencemanagement.view.holiday.management.model.HolidayManagementModel;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.*;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayManagementController")
@RequestScoped
public class HolidayManagementController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayManagementController.class);

    @ManagedProperty(value = "#{holidayManagementModel}")
    HolidayManagementModel holidayManagementModel;

    @ManagedProperty(value = "#{graphHelper}")
    private GraphHelper graphHelper;

    public List<HolidayAllowanceViewBean> getHolidayAllowanceList() {
        List<HolidayAllowanceViewBean> holidayAllowanceViewBeanList = new ArrayList<HolidayAllowanceViewBean>();
        holidayAllowanceViewBeanList.add(holidayManagementModel.getHolidayAllowance());
        return holidayAllowanceViewBeanList;
    }

    public HolidayAllowanceViewBean getHolidayAllowance() {
        return holidayManagementModel.getHolidayAllowance();
    }

    public ScheduleModel getScheduleLazyModel() {
        return holidayManagementModel.getLazyEventModel();
    }

    public HolidayManagementModel getHolidayManagementModel() {
        return holidayManagementModel;
    }

    public void setHolidayManagementModel(HolidayManagementModel holidayManagementModel) {
        this.holidayManagementModel = holidayManagementModel;
    }

    public GraphHelper getGraphHelper() {
        return graphHelper;
    }

    public void setGraphHelper(GraphHelper graphHelper) {
        this.graphHelper = graphHelper;
    }

    public void requestHoliday() {
        holidayManagementModel.requestHoliday();
    }

    public void clearHolidayRequest() {
        holidayManagementModel.clearHolidayRequest();
    }

    public DonutChartModel getHolidayBalanceDonutModel() {

        HolidayAllowanceViewBean holidayAllowance = holidayManagementModel.getHolidayAllowance();

        return graphHelper.buildHolidayAllowanceDonutChart(
                "Annual Leave Balance", holidayAllowance.getUsed(),
                holidayAllowance.getRemaining());
    }

    public BarChartModel getHolidayBarChartStats() {
        HolidayBreakdown holidayBreakdown =
                holidayManagementModel.getHolidayBreakdown();

        return graphHelper.buildHolidayBreakdownBarChart(
                "Annual Leave Breakdown",holidayBreakdown);
    }

    public List<HolidayRequestViewingBean> getRequestHistory() {

        return holidayManagementModel.getRequestHistory();
    }
}
