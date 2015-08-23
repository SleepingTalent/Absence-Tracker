package com.noveria.absencemanagement.view.holiday.management.controller;

import com.noveria.absencemanagement.model.holiday.annualleave.HolidayBreakdown;
import com.noveria.absencemanagement.model.holiday.annualleave.Month;
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

    public DonutChartModel getHolidayBalanceDonutModel() {

        HolidayAllowanceViewBean holidayAllowance = holidayManagementModel.getHolidayAllowance();
        DonutChartModel holidayDonutModel = new DonutChartModel();

        Map<String, Number> holidayData = new LinkedHashMap<String, Number>();

        holidayData.put("Hours Used", holidayAllowance.getUsed());
        holidayData.put("Hours Remaining", holidayAllowance.getRemaining());

        holidayDonutModel.setLegendPosition("e");
        holidayDonutModel.addCircle(holidayData);
        holidayDonutModel.setTitle("Holiday Balance");

        holidayDonutModel.setSliceMargin(5);
        holidayDonutModel.setShowDataLabels(true);
        holidayDonutModel.setDataFormat("value");

        return holidayDonutModel;
    }

    public BarChartModel getHolidayBarChartStats() {
        BarChartModel holidayStats = new BarChartModel();

        ChartSeries holiday = new ChartSeries();

        HolidayBreakdown holidayBreakdown = holidayManagementModel.getHolidayBreakdown();

        for (Map.Entry<Month, Integer> entry : holidayBreakdown.getMonthlyBreakdown().entrySet()) {
            holiday.set(entry.getKey().getDisplayName(), entry.getValue());
        }

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
