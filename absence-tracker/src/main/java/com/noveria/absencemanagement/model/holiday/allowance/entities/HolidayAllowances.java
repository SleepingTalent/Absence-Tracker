package com.noveria.absencemanagement.model.holiday.allowance.entities;

/**
 * Created by lynseymcgregor on 17/06/2015.
 */
public enum HolidayAllowances {

    DEFAULT(225);

    private int total;

    HolidayAllowances(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}
