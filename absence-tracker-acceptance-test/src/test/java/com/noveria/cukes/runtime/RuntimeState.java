package com.noveria.cukes.runtime;

import com.noveria.cukes.helpers.LoginDetails;
import com.noveria.cukes.helpers.selenium.WebDriverFactory;
import com.noveria.cukes.helpers.selenium.page.LoginPage;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import cucumber.api.Scenario;
import org.springframework.stereotype.Component;

@Component
public class RuntimeState {

    public static final String ABSENCE_TRACKER_URL = "http://localhost:4094/absence-tracker";

    public CucumberWebDriver getWebDriver() {
        return webDriver;
    }

    private CucumberWebDriver webDriver;
    private Scenario scenario;
    private LoginDetails loginDetails;

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public void initialise() {
        webDriver = WebDriverFactory.getWebDriver();
    }

    public void takeScreenShot() {
        if(webDriver != null) {
            webDriver.takeScreenShot(scenario);
        }
    }

    public void closeBrowser() {
        if(webDriver != null) {
            webDriver.closeBrowser();
        }
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver,ABSENCE_TRACKER_URL);
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }
}
