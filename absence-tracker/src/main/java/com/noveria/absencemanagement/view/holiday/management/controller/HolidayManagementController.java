package com.noveria.absencemanagement.view.holiday.management.controller;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayManagementController")
@RequestScoped
public class HolidayManagementController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayManagementController.class);

    @ManagedProperty(value = "#{holidayManagementModel}")
    HolidayManagementModel holidayManagementModel;

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

    public void requestHoliday() {
        holidayManagementModel.requestHoliday();
    }

    public void clearHolidayRequest() {
        holidayManagementModel.clearHolidayRequest();
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

    public List<HolidayRequestViewingBean> getRequestHistory() {
        return holidayManagementModel.getRequestHistory();
    }
}
