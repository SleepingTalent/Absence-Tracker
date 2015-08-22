package com.noveria.absencemanagement.model.absence.entity;

/**
 * Created by lynseymcgregor on 22/08/2015.
 */
public class AbsenceData {

    private String absenceType;
    private int total;

    public String getAbsenceType() {
        return absenceType;
    }

    public int getTotal() {
        return total;
    }

    public void setAbsenceType(String absenceType) {
        this.absenceType = absenceType;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
