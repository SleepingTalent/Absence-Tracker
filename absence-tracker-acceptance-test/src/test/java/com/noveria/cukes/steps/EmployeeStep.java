package com.noveria.cukes.steps;

import com.noveria.cukes.helpers.DateFactory;
import com.noveria.cukes.helpers.db.DBHelper;
import com.noveria.cukes.helpers.selenium.page.HolidayManagementPage;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

/**
 * Created by lynseymcgregor on 09/06/2015.
 */
@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class EmployeeStep {

    Logger logger = LoggerFactory.getLogger(SetUpAndTearDownStep.class);

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    DBHelper dbHelper;

    @Autowired
    DateFactory dateFactory;

    @And("^they request a holiday from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void they_request_a_holiday_from_to(String from, String to) throws Throwable {
        HolidayManagementPage holidayManagementPage = runtimeState.getPageFactory().getHolidayManagementPage();

        holidayManagementPage.selectTodaysDateForHolidayRequestStart();
        holidayManagementPage.selectTodaysDateForHolidayRequestEnd();
        holidayManagementPage.clickHolidayRequestBtn();
    }

    @Then("^a holiday request is created$")
    public void a_holiday_request_is_created() throws Throwable {
        HolidayManagementPage holidayManagementPage = runtimeState.getPageFactory().getHolidayManagementPage();
        holidayManagementPage.assertHolidayRequestInPendingState();

    }
}
