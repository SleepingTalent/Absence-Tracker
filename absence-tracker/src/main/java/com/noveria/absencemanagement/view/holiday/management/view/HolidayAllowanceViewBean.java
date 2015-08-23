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


}
