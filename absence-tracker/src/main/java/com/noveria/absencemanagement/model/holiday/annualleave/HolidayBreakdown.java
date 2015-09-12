package com.noveria.absencemanagement.model.holiday.annualleave;

import com.noveria.absencemanagement.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by lynseymcgregor on 22/08/2015.
 */
public class HolidayBreakdown {

    private static final Logger logger = LoggerFactory.getLogger(HolidayBreakdown.class);


    private Map<Month,Integer> monthlyBreakdown = new LinkedHashMap<Month, Integer>();

    public HolidayBreakdown() {
        for(Month month : Month.values()) {
            monthlyBreakdown.put(month,0);
        }
    }

    public Map<Month, Integer> getMonthlyBreakdown() {
        return monthlyBreakdown;
    }

    public void updateTotal(Month month, int value) {
        int original = monthlyBreakdown.get(month);
        monthlyBreakdown.put(month, original + value);
    }

    public void updateHolidayBreakdown(Date startDate, Date endDate) {
        try {

            Month startMonth = getMonth(startDate);
            Month endMonth = getMonth(endDate);

            if (startMonth.equals(endMonth)) {
                updateTotalFromSameMonth(startDate, endDate, startMonth);
            }else {
                updateTotalForMoreThanOneMonth(startDate, endDate, startMonth, endMonth);
            }

        } catch (Exception e) {
            logger.error("Error Updating with Start("+startDate+") End("+endDate+")",e);
        }
    }

    private void updateTotalForMoreThanOneMonth(Date startDate, Date endDate, Month startMonth, Month endMonth) {
        Date startMonthEnd = getEndOfMonth(startDate);
        updateMonthlyTotal(startDate, startMonthEnd, startMonth);

        int monthDiff;

        if(endMonthIsWithinTheSameFinancialYear(startMonth, endMonth)) {

            Date endMonthStart = getStartOfMonth(endDate);
            updateMonthlyTotal(endMonthStart, endDate, endMonth);
            monthDiff = Month.getMonthDifference(startMonth,endMonth);

        } else {
            monthDiff = getDifferenceFromStartMonthToEndOfFinancialYear(startMonth);
        }

        if(leaveIfOverTwoMonths(monthDiff)) {
            updateTotalsForMonthsInBetweenStartAndEndMonths(startDate, startMonth, monthDiff);
        }
    }

    private void updateTotalsForMonthsInBetweenStartAndEndMonths(Date startDate, Month startMonth, int monthDiff) {
        for (int i = 1; i < monthDiff; i++) {
            Date inbetweenDate = getInbetweenMonth(startDate, startMonth.getMonthIndex() + i);

            Date inbetweenMonthStart = getStartOfMonth(inbetweenDate);
            Date inbetweenMonthEnd = getEndOfMonth(inbetweenDate);

            updateMonthlyTotal(inbetweenMonthStart, inbetweenMonthEnd, Month.getMonthFromIndex(startMonth.getMonthIndex() + i));
        }
    }

    private boolean leaveIfOverTwoMonths(int monthDiff) {
        return monthDiff > 1;
    }

    private int getDifferenceFromStartMonthToEndOfFinancialYear(Month startMonth) {
        return (Month.MARCH.getFinancialYearIndex() - startMonth.getFinancialYearIndex()) + 1;
    }

    private boolean endMonthIsWithinTheSameFinancialYear(Month startMonth, Month endMonth) {
        return endMonth.getFinancialYearIndex() > startMonth.getFinancialYearIndex();
    }

    private void updateTotalFromSameMonth(Date startDate, Date endDate, Month startMonth) {
        updateMonthlyTotal(startDate,endDate,startMonth);
    }

    private void updateMonthlyTotal(Date monthStart, Date monthEnd, Month month) {
        int monthTotal = DateUtil.getWorkingDaysBetweenTwoDates(monthStart, monthEnd);
        updateTotal(month, monthTotal);
    }

    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    private Month getMonth(Date date) {
        Calendar cal = getCalendar(date);
        return Month.getMonthFromIndex(cal.get(Calendar.MONTH));
    }

    private Date getInbetweenMonth(Date date, int monthIndex) {
        Calendar cal = getCalendar(date);
        cal.set(Calendar.MONTH, monthIndex);
        return cal.getTime();
    }

    private Date getStartOfMonth(Date date) {
        Calendar cal = getCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    private Date getEndOfMonth(Date date) {
        Calendar cal = getCalendar(date);
        Month month = getMonth(date);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.MONTH, month.getMonthIndex()+1);
        return cal.getTime();
    }

}
