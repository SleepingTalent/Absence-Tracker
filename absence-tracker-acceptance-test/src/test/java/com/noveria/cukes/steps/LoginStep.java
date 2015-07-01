package com.noveria.cukes.steps;

import com.noveria.cukes.helpers.LoginDetails;
import com.noveria.cukes.helpers.UserType;
import com.noveria.cukes.helpers.selenium.page.dashboard.WelcomePage;
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

        validUser.setUsername(foundUserType.getUsername());
        validUser.setPassword(foundUserType.getPassword());
        validUser.setUserType(foundUserType);

        runtimeState.setLoginDetails(validUser);
    }

    @When("^the user logs in$")
    public void the_user_logs_in() throws Throwable {
        LoginPage loginPage = runtimeState.getPageFactory().getLoginPage();

        loginPage.navigateToLoginPage();
        loginPage.inputUserName(runtimeState.getLoginDetails().getUsername());
        loginPage.inputPassword(runtimeState.getLoginDetails().getPassword());

        runtimeState.takeScreenShot();
        loginPage.clickLoginButton();
    }

    @Then("^they have the expected user features$")
    public void they_have_the_expected_user_features() throws Throwable {
        runtimeState.takeScreenShot();

        WelcomePage welcomePage = runtimeState.getPageFactory().getWelcomePage();
        welcomePage.assertPagePresent();

        if(runtimeState.getLoginDetails().getUserType().equals(UserType.ADMIN)) {
            welcomePage.getFeaturesMenu().assertAdminFeaturesPresent();
        } else if (runtimeState.getLoginDetails().getUserType().equals(UserType.MANAGER)) {
           welcomePage.getFeaturesMenu().assertManagerFeaturesPresent();
        } else if (runtimeState.getLoginDetails().getUserType().equals(UserType.EMPLOYEE)) {
            welcomePage.getFeaturesMenu().assertEmployeeFeaturesPresent();
        }
    }

    @Then("^a login error is displayed$")
    public void a_login_error_is_displayed() throws Throwable {
        LoginPage loginPage = runtimeState.getPageFactory().getLoginPage();
        loginPage.assertLoginErrorIsDisplayed();

        runtimeState.takeScreenShot();
    }

    @When("^the user logs out$")
    public void the_user_logs_out() throws Throwable {
        WelcomePage welcomePage = runtimeState.getPageFactory().getWelcomePage();
        welcomePage.getFeaturesMenu().clickLogout();
    }

    @Then("^they are redirected to the login page$")
    public void they_are_redirected_to_the_login_page() throws Throwable {
        LoginPage loginPage = runtimeState.getPageFactory().getLoginPage();
        loginPage.assertPagePresent();

        runtimeState.takeScreenShot();
    }

    @When("^the Employee logs in$")
    public void the_Employee_logs_in() throws Throwable {
        LoginPage loginPage = runtimeState.getPageFactory().getLoginPage();

        loginPage.navigateToLoginPage();

        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUsername(runtimeState.getEmployee().getUsername());
        loginDetails.setPassword(runtimeState.getEmployee().getPassword());
        loginDetails.setUserType(UserType.EMPLOYEE);

        runtimeState.setLoginDetails(loginDetails);

        loginPage.inputUserName(loginDetails.getUsername());
        loginPage.inputPassword(loginDetails.getPassword());

        runtimeState.takeScreenShot();
        loginPage.clickLoginButton();
    }

    @When("^the Employee logs out$")
    public void the_Employee_logs_out() throws Throwable {
        WelcomePage welcomePage = runtimeState.getPageFactory().getWelcomePage();
        welcomePage.assertPagePresent();
        welcomePage.getFeaturesMenu().clickLogout();
    }

    @When("^the user logs in and selects admin features$")
    public void the_user_logs_in_and_selects_admin_features() throws Throwable {
        LoginPage loginPage = runtimeState.getPageFactory().getLoginPage();

        loginPage.navigateToLoginPage();
        loginPage.inputUserName(runtimeState.getLoginDetails().getUsername());
        loginPage.inputPassword(runtimeState.getLoginDetails().getPassword());

        runtimeState.takeScreenShot();
        loginPage.clickLoginButton();

        WelcomePage welcomePage = runtimeState.getPageFactory().getWelcomePage();
        welcomePage.assertPagePresent();
        runtimeState.takeScreenShot();
        welcomePage.getFeaturesMenu().clickSystemAdmin();
    }
}
