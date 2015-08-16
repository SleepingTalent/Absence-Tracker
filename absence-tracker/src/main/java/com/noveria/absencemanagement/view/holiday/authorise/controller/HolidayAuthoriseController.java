package com.noveria.absencemanagement.view.holiday.authorise.controller;

import com.noveria.absencemanagement.view.holiday.authorise.model.HolidayAuthoriseModel;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public DonutChartModel getHolidayAllowance() {
        DonutChartModel holidayDonutModel = new DonutChartModel();

        Map<String, Number> holidayData = new LinkedHashMap<String, Number>();

        holidayData.put("Used", 150);
        holidayData.put("Remaining", 250);

        holidayDonutModel.setLegendPosition("e");
        holidayDonutModel.addCircle(holidayData);
        holidayDonutModel.setTitle("Department Holiday Balance (Hours)");

        holidayDonutModel.setSliceMargin(5);
        holidayDonutModel.setShowDataLabels(true);
        holidayDonutModel.setDataFormat("value");

        return holidayDonutModel;
    }

    public List<DonutChartModel> getEmployeeHolidayAllowances() {
        List<DonutChartModel> donutChartModels = new ArrayList<DonutChartModel>();

        DonutChartModel holidayDonutModel = new DonutChartModel();

        Map<String, Number> holidayData = new LinkedHashMap<String, Number>();
        holidayData.put("Used", 100);
        holidayData.put("Remaining", 200);

        holidayDonutModel.setLegendPosition("n");
        holidayDonutModel.addCircle(holidayData);
        holidayDonutModel.setTitle("Jane Worker");
        holidayDonutModel.setSliceMargin(5);
        holidayDonutModel.setShowDataLabels(true);
        holidayDonutModel.setDataFormat("value");

        donutChartModels.add(holidayDonutModel);

        DonutChartModel holidayDonutModel2 = new DonutChartModel();

        Map<String, Number> holidayData2 = new LinkedHashMap<String, Number>();
        holidayData2.put("Used", 150);
        holidayData2.put("Remaining", 150);
        holidayDonutModel2.setLegendPosition("n");
        holidayDonutModel2.addCircle(holidayData2);
        holidayDonutModel2.setTitle("Dave Worker");
        holidayDonutModel2.setSliceMargin(5);
        holidayDonutModel2.setShowDataLabels(true);
        holidayDonutModel2.setDataFormat("value");

        donutChartModels.add(holidayDonutModel2);

        DonutChartModel holidayDonutModel3 = new DonutChartModel();

        Map<String, Number> holidayData3 = new LinkedHashMap<String, Number>();
        holidayData3.put("Used", 50);
        holidayData3.put("Remaining", 250);
        holidayDonutModel3.setLegendPosition("n");
        holidayDonutModel3.addCircle(holidayData3);
        holidayDonutModel3.setTitle("Another Worker");
        holidayDonutModel3.setSliceMargin(5);
        holidayDonutModel3.setShowDataLabels(true);
        holidayDonutModel3.setDataFormat("value");

        donutChartModels.add(holidayDonutModel3);

        return donutChartModels;
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
        holidayStats.setTitle("Department Annual Holiday Breakdown");
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
