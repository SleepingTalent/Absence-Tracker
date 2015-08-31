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

            int startMonth = getMonth(startDate);
            int endMonth = getMonth(endDate);

            if (startMonth == endMonth) {
                int totalDays = DateUtil.getWorkingDaysBetweenTwoDates(startDate, endDate);

                Month month = Month.getMonthFromIndex(startMonth);
                updateTotal(month, totalDays);
            } else {
                Date endOfStartMonth = getEndOfStartMonth(endDate);
                Date startOfEndMonth = getStartOfEndMonth(endDate);

                int startMonthTotal = DateUtil.getWorkingDaysBetweenTwoDates(startDate, endOfStartMonth);
                int endMonthTotal = DateUtil.getWorkingDaysBetweenTwoDates(startOfEndMonth, endDate);

                Month startMon = Month.getMonthFromIndex(startMonth);
                updateTotal(startMon, startMonthTotal);

                Month endMon = Month.getMonthFromIndex(endMonth);
                updateTotal(endMon, endMonthTotal);
            }

        } catch (Exception e) {
            logger.error("Error Updating with Start("+startDate+") End("+endDate+")",e);
        }
    }

    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    private int getMonth(Date date) {
        Calendar cal = getCalendar(date);
        return cal.get(Calendar.MONTH);
    }

    private Date getEndOfStartMonth(Date date) {
        Calendar cal = getCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    private Date getStartOfEndMonth(Date date) {
        Calendar cal = getCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

}
