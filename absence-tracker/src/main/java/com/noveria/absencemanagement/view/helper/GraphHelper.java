package com.noveria.absencemanagement.view.helper;

/**
 * Created by lynseymcgregor on 23/08/2015.
 */

import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.absence.entity.AbsenceReason;
import com.noveria.absencemanagement.model.holiday.annualleave.HolidayBreakdown;
import com.noveria.absencemanagement.model.holiday.annualleave.Month;
import org.primefaces.model.chart.*;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GraphHelper {

    public DonutChartModel buildHolidayAllowanceDonutChart(String title, int used, int remaining) {
        DonutChartModel holidayDonutModel = new DonutChartModel();

        Map<String, Number> holidayData = new LinkedHashMap<String, Number>();

        holidayData.put("Hours Used", used);
        holidayData.put("Hours Remaining", remaining);

        holidayDonutModel.setLegendPosition("e");
        holidayDonutModel.addCircle(holidayData);
        holidayDonutModel.setTitle(title);

        holidayDonutModel.setSliceMargin(5);
        holidayDonutModel.setShowDataLabels(true);
        holidayDonutModel.setDataFormat("value");

        return holidayDonutModel;

    }

    public BarChartModel buildHolidayBreakdownBarChart(String title, HolidayBreakdown holidayBreakdown) {
        BarChartModel holidayStats = new BarChartModel();

        ChartSeries holiday = new ChartSeries();

        for (Map.Entry<Month, Integer> entry : holidayBreakdown.getMonthlyBreakdown().entrySet()) {
            holiday.set(entry.getKey().getDisplayName(), entry.getValue());
        }

        holidayStats.addSeries(holiday);
        holidayStats.setTitle(title);
        holidayStats.setExtender("chartExtender");

        Axis yAxis = holidayStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);
        yAxis.setMax(30);

        return holidayStats;
    }

    public BarChartModel buildAbsenceStats(String title, List<AbsenceData> absenceDataList ) {
        BarChartModel authorisedAbsenceStats = new BarChartModel();

        ChartSeries absence = new ChartSeries();

        for(AbsenceData absenceData : absenceDataList) {
            absence.set(AbsenceReason.findByName(
                            absenceData.getAbsenceType()).getDisplayName(),
                    absenceData.getTotal());
        }

        authorisedAbsenceStats.addSeries(absence);
        authorisedAbsenceStats.setTitle(title);
        authorisedAbsenceStats.setExtender("chartExtender");

        Axis yAxis = authorisedAbsenceStats.getAxis(AxisType.Y);
        yAxis.setLabel("Days");
        yAxis.setMin(0);
        yAxis.setMax(100);

        return authorisedAbsenceStats;
    }

}
