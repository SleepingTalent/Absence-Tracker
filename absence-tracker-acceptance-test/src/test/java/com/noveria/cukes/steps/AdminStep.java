package com.noveria.cukes.steps;

import com.noveria.cukes.helpers.selenium.page.dashboard.DashboardPage;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by lynseymcgregor on 09/06/2015.
 */
@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class AdminStep {

    Logger logger = LoggerFactory.getLogger(SetUpAndTearDownStep.class);

    @Autowired
    RuntimeState runtimeState;

    @And("^they create a Department without a name$")
    public void they_create_a_without_a() throws Throwable {

        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();

        try {
            dashboardPage.createDepartmentWithEmptyName(runtimeState, true);
        } catch (SeleniumTimeoutException set) {
            dashboardPage.createDepartmentWithEmptyName(runtimeState, false);
        }
    }

    @Then("^a \"([^\"]*)\" \"([^\"]*)\" validation error is displayed$")
    public void a_validation_error_is_displayed(String title, String message) throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();
        dashboardPage.assertValidationErrorIsDisplayed(title, message);
        runtimeState.takeScreenShot();
    }

    @And("^checks that the \"([^\"]*)\" department does not exist$")
    public void checks_that_the_department_does_not_exist(String departmentName) throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();

        try {
            dashboardPage.checkThatTheDepartmentDoesNotExist(runtimeState, departmentName, true);
        } catch (SeleniumTimeoutException set) {
            dashboardPage.checkThatTheDepartmentDoesNotExist(runtimeState, departmentName, false);
        }
    }

    @And("^they create a Department called \"([^\"]*)\"$")
    public void they_create_a_Department_called(String departmentName) throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();

        try {
            dashboardPage.createDepartmentWithName(runtimeState, departmentName, true);
        } catch (SeleniumTimeoutException set) {
            dashboardPage.createDepartmentWithName(runtimeState, departmentName, false);
        }
    }

    @Then("^the \"([^\"]*)\" Department is created$")
    public void the_Department_is_created(String departmentName) throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();

        try {
            dashboardPage.checkThatTheDepartmentExist(runtimeState, departmentName, true);
        } catch (SeleniumTimeoutException set) {
            dashboardPage.checkThatTheDepartmentExist(runtimeState, departmentName, false);
        }
    }

    @And("^the \"([^\"]*)\" Department is already created$")
    public void the_Department_is_already_created(String departmentName) throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();

        try {
            dashboardPage.checkThatTheDepartmentExist(runtimeState, departmentName, true);
        } catch (SeleniumTimeoutException set) {
            dashboardPage.checkThatTheDepartmentExist(runtimeState, departmentName, false);
        }
    }
}
