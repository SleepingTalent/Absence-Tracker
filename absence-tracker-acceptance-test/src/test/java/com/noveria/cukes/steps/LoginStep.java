package com.noveria.cukes.steps;

import com.noveria.cukes.helpers.LoginDetails;
import com.noveria.cukes.helpers.UserType;
import com.noveria.cukes.helpers.selenium.page.dashboard.DashboardPage;
import com.noveria.cukes.helpers.selenium.page.LoginPage;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.fail;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class LoginStep {

    Logger logger = LoggerFactory.getLogger(SetUpAndTearDownStep.class);

    @Autowired
    RuntimeState runtimeState;

    @Given("^an invalid user$")
    public void an_invalid_user() throws Throwable {
        LoginDetails invalidUser = new LoginDetails();
        invalidUser.setUsername("wrong");
        invalidUser.setPassword("wrong");

        runtimeState.setLoginDetails(invalidUser);

    }

    @Given("^a valid \"(.*?)\" user$")
    public void a_valid_user(String userType) throws Throwable {
        LoginDetails validUser = new LoginDetails();

        UserType foundUserType = UserType.findByName(userType);

        if (foundUserType.equals(UserType.ADMIN)) {
            validUser.setUsername("admin");
            validUser.setPassword("password");
            validUser.setUserType(foundUserType);
        } else if (foundUserType.equals(UserType.MANAGER)) {
            validUser.setUsername("manager");
            validUser.setPassword("password");
            validUser.setUserType(foundUserType);
        } else if (foundUserType.equals(UserType.EMPLOYEE)) {
            validUser.setUsername("employee");
            validUser.setPassword("password");
            validUser.setUserType(foundUserType);
        } else if (foundUserType.equals(UserType.NOROLE)) {
            validUser.setUsername("norole");
            validUser.setPassword("password");
            validUser.setUserType(foundUserType);
        } else if (foundUserType.equals(UserType.UNKNOWNROLE)) {
        validUser.setUsername("unknownrole");
        validUser.setPassword("password");
        validUser.setUserType(foundUserType);
    }

        runtimeState.setLoginDetails(validUser);
    }

    @When("^the user logs in$")
    public void the_user_logs_in() throws Throwable {
        LoginPage loginPage =runtimeState.getLoginPage();
        loginPage.navigateToLoginPage();

        loginPage.inputUserName(runtimeState.getLoginDetails().getUsername());
        loginPage.inputPassword(runtimeState.getLoginDetails().getPassword());

        runtimeState.takeScreenShot();
        loginPage.clickLoginButton();
    }


    @Then("^they are redirected to the appropriate dashboard$")
    public void they_are_redirected_to_the_dashboard() throws Throwable {
        runtimeState.takeScreenShot();
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();

        if(runtimeState.getLoginDetails().getUserType().equals(UserType.ADMIN)) {
            dashboardPage.assertAdminTextPresent();
        } else if (runtimeState.getLoginDetails().getUserType().equals(UserType.MANAGER)) {
           dashboardPage.assertManagerTextPresent();
        } else if (runtimeState.getLoginDetails().getUserType().equals(UserType.EMPLOYEE)) {
           dashboardPage.assertEmployeeTextPresent();
        }
    }

    @Then("^a login error is displayed$")
    public void a_login_error_is_displayed() throws Throwable {
        LoginPage loginPage = runtimeState.getLoginPage();
        loginPage.assertLoginErrorIsDisplayed();
        runtimeState.takeScreenShot();
    }

    @When("^the user logs out$")
    public void the_user_logs_out() throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(runtimeState.getWebDriver());
        dashboardPage.assertPagePresent();
        dashboardPage.clickLogoutBtn();
    }

    @Then("^they are redirected to the login page$")
    public void they_are_redirected_to_the_login_page() throws Throwable {
        LoginPage loginPage = runtimeState.getLoginPage();
        loginPage.assertPagePresent();
        runtimeState.takeScreenShot();
    }
}
