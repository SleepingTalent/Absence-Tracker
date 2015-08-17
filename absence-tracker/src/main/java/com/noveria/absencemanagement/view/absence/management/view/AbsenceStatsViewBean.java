package com.noveria.absencemanagement.view.absence.management.view;

import org.primefaces.model.chart.BarChartModel;

/**
 * Created by lynseymcgregor on 17/08/2015.
 */
public class AbsenceStatsViewBean {

    BarChartModel authorisedAbsence;
    BarChartModel unAuthorisedAbsence;

    int index;

    public AbsenceStatsViewBean(int index) {
        this.index = index;
    }

    public BarChartModel getAuthorisedAbsence() {
        return authorisedAbsence;
    }

    public void setAuthorisedAbsence(BarChartModel authorisedAbsence) {
        this.authorisedAbsence = authorisedAbsence;
    }

    public BarChartModel getUnAuthorisedAbsence() {
        return unAuthorisedAbsence;
    }

    public void setUnAuthorisedAbsence(BarChartModel unAuthorisedAbsence) {
        this.unAuthorisedAbsence = unAuthorisedAbsence;
    }

    public int getIndex() {
        return index;
    }
}
