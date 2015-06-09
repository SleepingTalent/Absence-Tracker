package com.noveria.cukes.steps;

import com.noveria.cukes.helpers.selenium.page.dashboard.DashboardPage;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
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

    @And("^they create a \"([^\"]*)\" without a \"([^\"]*)\"$")
    public void they_create_a_without_a(String entity, String missingField) throws Throwable {

        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();

        if(entity.equalsIgnoreCase("Department")) {
            dashboardPage.getAdminMenu().openCreateMenu();
            dashboardPage.getAdminMenu().clickOnCreateDepartment();

            if(missingField.equalsIgnoreCase("name")) {
                dashboardPage.getCreateDepartmentDialog().setName("");
            }

            runtimeState.takeScreenShot();
            dashboardPage.getCreateDepartmentDialog().clickCreateBtn();
        }

    }

    @Then("^a \"([^\"]*)\" \"([^\"]*)\" validation error is displayed$")
    public void a_validation_error_is_displayed(String title, String message) throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();
        dashboardPage.assertValidationErrorIsDisplayed(title,message);
        runtimeState.takeScreenShot();
    }
}
