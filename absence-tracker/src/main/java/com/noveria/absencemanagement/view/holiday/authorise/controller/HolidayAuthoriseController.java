package com.noveria.absencemanagement.view.holiday.authorise.controller;

import com.noveria.absencemanagement.view.holiday.authorise.model.HolidayAuthoriseModel;
import com.noveria.absencemanagement.view.holiday.management.model.HolidayManagementModel;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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

    public HolidayAllowanceViewBean getHolidayAllowance() {
        HolidayAllowanceViewBean holidayAllowance = new HolidayAllowanceViewBean();
        holidayAllowance.setTotal(450);
        holidayAllowance.setUsed(100);
        holidayAllowance.setRemaining(350);
        return holidayAllowance;
    }

    public BarChartModel getHolidayBarChartStats() {
        BarChartModel holidayStats = new BarChartModel();

        ChartSeries holiday = new ChartSeries();

        holiday.set("Apr", 5);
        holiday.set("May", 1);
        holiday.set("Jun", 15);
        holiday.set("Jul", 1);
        holiday.set("Aug", 2);
        holiday.set("Sep", 1);
        holiday.set("Oct", 5);
        holiday.set("Nov", 1);
        holiday.set("Dec", 5);
        holiday.set("Jan", 1);
        holiday.set("Feb", 1);
        holiday.set("Mar", 2);

        holidayStats.addSeries(holiday);
        holidayStats.setTitle("Annual Holiday Breakdown");
        holidayStats.setExtender("chartExtender");

        Axis yAxis = holidayStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);
        yAxis.setMax(30);

        return holidayStats;
    }

    public HolidayRequestViewingBean getSelectedRequest() {
        return holidayAuthoriseModel.getSelectedRequest();
    }
}
