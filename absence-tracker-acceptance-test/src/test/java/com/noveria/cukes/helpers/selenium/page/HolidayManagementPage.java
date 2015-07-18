package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebDriverException;

import static org.junit.Assert.assertEquals;

/**
 * Created by lynseymcgregor on 02/07/2015.
 */
public class HolidayManagementPage extends Page {

    private static final String HOLIDAY_TOTAL = "holidayTotal";
    private static final String HOLIDAY_USED = "holidayUsed";
    private static final String HOLIDAY_REMAINING = "holidayRemaining";

    private static final String HOLIDAY_REQUEST_START = "requestHolidayForm:holidayStart";
    private static final String HOLIDAY_REQUEST_END = "requestHolidayForm:holidayEnd";
    private static final String HOLIDAY_REQUEST_BTN = "requestHolidayForm:requestHolidyBtn";
    public static final String CALENDAR_DATE_POPUP = "ui-datepicker-div";

    public HolidayManagementPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void assertDefaultHolidayTotalAllowance() {
        getPageHelper().findTableDataWithText("225");
    }

    public void assertDefaultHolidayUsedAllowance() {
        getPageHelper().findTableDataWithText("0");
    }

    public void assertDefaultHolidayRemainingAllowance() {
        getPageHelper().findTableDataWithText("225");
    }

    public void selectTodaysDateForHolidayRequestStart() {

        try {
            getPageHelper().findElementById(HOLIDAY_REQUEST_START,true).click();
            getPageHelper().findElementById(CALENDAR_DATE_POPUP,true);
            getPageHelper().findElementByClass("ui-datepicker-today").click();
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findElementById(HOLIDAY_REQUEST_START).click();
            getPageHelper().findElementById(CALENDAR_DATE_POPUP);
            getPageHelper().findElementByClass("ui-datepicker-today").click();
        } catch (WebDriverException we) {
            getPageHelper().findElementById(HOLIDAY_REQUEST_START).click();
            getPageHelper().findElementById(CALENDAR_DATE_POPUP);
            getPageHelper().findElementByClass("ui-datepicker-today").click();
        }
    }

    public void selectTodaysDateForHolidayRequestEnd() {

        try {
            getPageHelper().findElementById(HOLIDAY_REQUEST_END,true).click();
            getPageHelper().findElementById(CALENDAR_DATE_POPUP);
            getPageHelper().findElementByClass("ui-datepicker-today").click();
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findElementById(HOLIDAY_REQUEST_END).click();
            getPageHelper().findElementById(CALENDAR_DATE_POPUP);
            getPageHelper().findElementByClass("ui-datepicker-today").click();
        } catch (WebDriverException we) {
            getPageHelper().findElementById(HOLIDAY_REQUEST_END).click();
            getPageHelper().findElementById(CALENDAR_DATE_POPUP);
            getPageHelper().findElementByClass("ui-datepicker-today").click();
        }
    }

    public void clickHolidayRequestBtn() {
        try {
            getPageHelper().findElementById(HOLIDAY_REQUEST_BTN,true).click();
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findElementById(HOLIDAY_REQUEST_BTN).click();
        } catch (WebDriverException we) {
            getPageHelper().findElementById(HOLIDAY_REQUEST_BTN).click();
        }
    }

    public void assertHolidayRequestInPendingState() {
        getPageHelper().findTableRowWithText("Pending");
    }
}
