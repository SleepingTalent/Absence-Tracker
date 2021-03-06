package com.noveria.absencemanagement.model.holiday.annualleave;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by lynseymcgregor on 22/08/2015.
 */
public class HolidayBreakdownTest {

    HolidayBreakdown holidayBreakdown;

    @Before
    public void setUp() {
       holidayBreakdown = new HolidayBreakdown();
    }

    @Test
    public void monthlyBreakdown_initialised_asExpected() {
        assertEquals(12, holidayBreakdown.getMonthlyBreakdown().size());

        for(Month month : Month.values()) {
            assertEquals(0, holidayBreakdown.getMonthlyBreakdown().get(month).intValue());
        }
    }

    @Test
    public void monthlyBreakdown_addTotal_asExpected() {
        holidayBreakdown.updateTotal(Month.APRIL,10);
        assertEquals(10, holidayBreakdown.getMonthlyBreakdown().get(Month.APRIL).intValue());

        holidayBreakdown.updateTotal(Month.APRIL,5);
        assertEquals(15, holidayBreakdown.getMonthlyBreakdown().get(Month.APRIL).intValue());
    }

    @Test
    public void updateHolidayBreakdown_asExpected_whenHolidayFallsInTheSameMonth() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = dateFormat.parse("01-01-2015");
        Date endDate = dateFormat.parse("10-01-2015");

        holidayBreakdown.updateHolidayBreakdown(startDate,endDate);

        assertEquals(7, holidayBreakdown.getMonthlyBreakdown().get(Month.JANUARY).intValue());
    }

    @Test
    public void updateHolidayBreakdown_asExpected_whenHolidayFallsOverTwoMonths() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = dateFormat.parse("20-04-2015");
        Date endDate = dateFormat.parse("07-05-2015");

        holidayBreakdown.updateHolidayBreakdown(startDate,endDate);

        assertEquals(9, holidayBreakdown.getMonthlyBreakdown().get(Month.APRIL).intValue());
        assertEquals(5, holidayBreakdown.getMonthlyBreakdown().get(Month.MAY).intValue());
    }

    @Test
    public void updateHolidayBreakdown_asExpected_whenHolidayFallsOverMultipleMonths() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = dateFormat.parse("20-04-2015");
        Date endDate = dateFormat.parse("07-07-2015");

        holidayBreakdown.updateHolidayBreakdown(startDate, endDate);

        assertEquals(9, holidayBreakdown.getMonthlyBreakdown().get(Month.APRIL).intValue());
        assertEquals(21, holidayBreakdown.getMonthlyBreakdown().get(Month.MAY).intValue());
        assertEquals(22, holidayBreakdown.getMonthlyBreakdown().get(Month.JUNE).intValue());
        assertEquals(5, holidayBreakdown.getMonthlyBreakdown().get(Month.JULY).intValue());
    }

    @Test
    public void updateHolidayBreakdown_asExpected_whenHolidayFallsOverMultipleMonthsIntoTheFollowingYear() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = dateFormat.parse("20-01-2015");
        Date endDate = dateFormat.parse("07-04-2015");

        holidayBreakdown.updateHolidayBreakdown(startDate, endDate);

        assertEquals(9, holidayBreakdown.getMonthlyBreakdown().get(Month.JANUARY).intValue());
        assertEquals(21, holidayBreakdown.getMonthlyBreakdown().get(Month.FEBURARY).intValue());
        assertEquals(23, holidayBreakdown.getMonthlyBreakdown().get(Month.MARCH).intValue());
        assertEquals(0, holidayBreakdown.getMonthlyBreakdown().get(Month.APRIL).intValue());
    }

    @Test
    public void updateHolidayBreakdown_asExpected_whenHolidayFallsOverAdjacentMonthsIntoTheFollowingYear() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = dateFormat.parse("20-03-2015");
        Date endDate = dateFormat.parse("07-04-2015");

        holidayBreakdown.updateHolidayBreakdown(startDate, endDate);

        assertEquals(8, holidayBreakdown.getMonthlyBreakdown().get(Month.MARCH).intValue());
        assertEquals(0, holidayBreakdown.getMonthlyBreakdown().get(Month.APRIL).intValue());
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
}
