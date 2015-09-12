package com.noveria.absencemanagement.model.holiday.annualleave;

/**
 * Created by lynseymcgregor on 22/08/2015.
 */
public enum Month {
    APRIL(3,"Apr",0),MAY(4,"May",1),JUNE(5,"Jun",2),JULY(6,"Jul",3),AUGUST(7,"Aug",4),
    SEPTEMBER(8,"Sep",5),OCTOBER(9,"Oct",6),NOVEMBER(10,"Nov",7),DECEMBER(11,"Dec",8),
    JANUARY(0,"Jan",9),FEBURARY(1,"Feb",10),MARCH(2,"Mar",11);

    private int monthIndex;
    private int financialYearIndex;
    private String displayName;

    Month(int monthIndex, String displayName, int financialYearIndex) {
        this.monthIndex = monthIndex;
        this.displayName = displayName;
        this.financialYearIndex = financialYearIndex;
    }

    public int getMonthIndex() {
        return monthIndex;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getFinancialYearIndex() {
        return financialYearIndex;
    }

    public static Month getMonthFromIndex(int monthIndex) {

        for(Month type : Month.values()) {
            if(type.getMonthIndex() == monthIndex) {
                return type;
            }
        }

        throw new RuntimeException(monthIndex+" : index not found!");
    }

    public static int getMonthDifference(Month startMonth, Month endMonth) {
        return endMonth.getMonthIndex() - startMonth.getMonthIndex();
    }
}
