package com.noveria.absencemanagement.view.holiday.management.view;

import org.primefaces.model.chart.DonutChartModel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
public class HolidayAllowanceViewBean {

    int used;

    int total;

    int remaining;

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public DonutChartModel getDonutModel() {
        DonutChartModel holidayDonutModel = new DonutChartModel();

        Map<String, Number> holidayData = new LinkedHashMap<String, Number>();

        holidayData.put("Used", used);
        holidayData.put("Remaining", remaining);

        holidayDonutModel.setLegendPosition("n");
        holidayDonutModel.addCircle(holidayData);
        holidayDonutModel.setSeriesColors("1d5198,a7c2ee");

        return holidayDonutModel;
    }

    public DonutChartModel getDetailedDonutModel() {
        DonutChartModel holidayDonutModel = new DonutChartModel();

        Map<String, Number> holidayData = new LinkedHashMap<String, Number>();

        holidayData.put("Used", used);
        holidayData.put("Remaining", remaining);

        holidayDonutModel.setLegendPosition("e");
        holidayDonutModel.addCircle(holidayData);
        holidayDonutModel.setTitle("Holiday Balance (Hours)");

        holidayDonutModel.setSliceMargin(5);
        holidayDonutModel.setShowDataLabels(true);
        holidayDonutModel.setDataFormat("value");

        return holidayDonutModel;
    }
}
