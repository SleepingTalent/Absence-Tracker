package com.noveria.absencemanagement.view.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lynseymcgregor on 28/06/2015.
 */
public class DateHelper {

    public static Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }

    public static Date daysInPast(int daysPast) {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) - daysPast);
        return t.getTime();
    }

    public static Date daysInFuture(int daysInTheFuture) {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + daysInTheFuture);
        return t.getTime();
    }
}
