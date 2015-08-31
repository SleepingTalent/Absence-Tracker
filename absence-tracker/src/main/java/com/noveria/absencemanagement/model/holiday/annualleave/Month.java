package com.noveria.absencemanagement.model.holiday.annualleave;

/**
 * Created by lynseymcgregor on 22/08/2015.
 */
public enum Month {
    APRIL(3,"Apr"),MAY(4,"May"),JUNE(5,"Jun"),JULY(6,"Jul"),AUGUST(7,"Aug"),
    SEPTEMBER(8,"Sep"),OCTOBER(9,"Oct"),NOVEMBER(10,"Nov"),DECEMBER(11,"Dec"),
    JANUARY(0,"Jan"),FEBURARY(1,"Feb"),MARCH(2,"Mar");

    private int monthIndex;
    private String displayName;

    Month(int monthIndex, String displayName) {
        this.monthIndex = monthIndex;
        this.displayName = displayName;
    }

    public int getMonthIndex() {
        return monthIndex;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Month getMonthFromIndex(int monthIndex) {

        for(Month type : Month.values()) {
            if(type.getMonthIndex() == monthIndex) {
                return type;
            }
        }

        throw new RuntimeException(monthIndex+" : index not found!");
    }
}
