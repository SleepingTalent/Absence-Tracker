package com.noveria.cukes.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class ExampleStep {

    Logger logger = LoggerFactory.getLogger(SetUpAndTearDownStep.class);

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

}
