package com.noveria.absencemanagement.model.holiday.allowance.entities;

/**
 * Created by lynseymcgregor on 17/07/2015.
 */
public enum WorkingHours {

    DEFAULT(8);

    private int dailyWorkingHours;

    WorkingHours(int dailyWorkingHours) {
        this.dailyWorkingHours = dailyWorkingHours;
    }

    public int getDailyWorkingHours() {
        return dailyWorkingHours;
    }
}
