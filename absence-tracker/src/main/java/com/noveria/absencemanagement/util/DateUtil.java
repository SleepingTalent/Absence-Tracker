package com.noveria.absencemanagement.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lynseymcgregor on 17/07/2015.
 */
public class DateUtil {

    public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 1;

        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 1;
        }

        do {
            //excluding start date
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

        return workDays;
    }

    public static void validateStartAndEndDates(Date startDate, Date endDate) throws InvalidDateException {

        if(startDate == null) {
            throw new InvalidDateException("Start Date must have a value!");
        }

        if(endDate == null) {
            throw new InvalidDateException("End Date must have a value!");
        }

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        Calendar today = Calendar.getInstance();

        if(startCal.getTimeInMillis() < today.getTimeInMillis()) {
            throw new InvalidDateException("Start Date must be current or future Date!");
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
          throw new InvalidDateException("Start Date after End Date!");
        }
    }

    public static void validateStartAndEndDatesForAbsence(Date startDate, Date endDate) throws InvalidDateException {

        if(startDate == null) {
            throw new InvalidDateException("Start Date must have a value!");
        }

        if(endDate == null) {
            throw new InvalidDateException("End Date must have a value!");
        }

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);


        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            throw new InvalidDateException("Start Date after End Date!");
        }
    }
}
