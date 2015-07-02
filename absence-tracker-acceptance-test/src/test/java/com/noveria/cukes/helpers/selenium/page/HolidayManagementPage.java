package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

import static org.junit.Assert.assertEquals;

/**
 * Created by lynseymcgregor on 02/07/2015.
 */
public class HolidayManagementPage extends Page {

    private static final String HOLIDAY_TOTAL = "holidayTotal";
    private static final String HOLIDAY_USED = "holidayUsed";
    private static final String HOLIDAY_REMAINING = "holidayRemaining";

    public HolidayManagementPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void assertDefaultHolidayTotalAllowance() {
        assertEquals("225", getPageHelper().findElementById(HOLIDAY_TOTAL).getText());
    }

    public void assertDefaultHolidayUsedAllowance() {
        assertEquals("0", getPageHelper().findElementById(HOLIDAY_USED).getText());
    }

    public void assertDefaultHolidayRemainingAllowance() {
        assertEquals("225", getPageHelper().findElementById(HOLIDAY_REMAINING).getText());
    }
}
