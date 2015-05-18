package com.noveria.cukes.steps;

import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class ExampleStep {

    Logger logger = LoggerFactory.getLogger(SetUpAndTearDownStep.class);

    @Autowired
    RuntimeState runtimeState;

    @Given("^some initial state$")
    public void some_initial_state() throws Throwable {
        logger.info("some_initial_state!");
    }

    @When("^an action is performed$")
    public void an_action_is_performed() throws Throwable {
        logger.info("an action is performed!");
    }

    @When("^an another action is performed$")
    public void an_another_action_is_performed() throws Throwable {
        logger.info("an another action is performed!");
    }

    @Then("^I will assert the outcome$")
    public void i_will_assert_the_outcome() throws Throwable {
        logger.info("I will assert the outcome!!");
    }

    @Given("^a User Navigates to the Welcome Page$")
    public void a_User_Navigates_to_the_Welcome_Page() throws Throwable {
        runtimeState.getWelcomePage().getPageHelper().navigateTo(RuntimeState.ABSENCE_TRACKER_URL);
        runtimeState.takeScreenShot();
    }

    @When("^they input the name \"(.*?)\"$")
    public void they_input_the_name(String name) throws Throwable {
        runtimeState.getWelcomePage().inputName(name);
        runtimeState.takeScreenShot();
        runtimeState.getWelcomePage().clickWelcomeButton();
    }

    @Then("^\"(.*?)\" is displayed on the Page$")
    public void is_displayed_on_the_Page(String name) throws Throwable {
        runtimeState.getWelcomePage().assertNameDisplayed(name);
        runtimeState.takeScreenShot();
    }

}
