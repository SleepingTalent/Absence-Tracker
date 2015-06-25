package com.noveria.cukes.runtime;

import com.noveria.cukes.helpers.LoginDetails;
import com.noveria.cukes.helpers.selenium.PageFactory;
import com.noveria.cukes.helpers.selenium.WebDriverFactory;
import com.noveria.cukes.helpers.selenium.page.LoginPage;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import cucumber.api.Scenario;
import org.springframework.stereotype.Component;

@Component
public class RuntimeState {

    public static final String ABSENCE_TRACKER_URL = "http://localhost:4094/absence-tracker";
    private String newDepartmentName = "CucumberTestDepartment";

    public CucumberWebDriver getWebDriver() {
        return webDriver;
    }

    private CucumberWebDriver webDriver;
    private Scenario scenario;
    private LoginDetails loginDetails;
    private PageFactory pageFactory;

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public void initialise() {
        webDriver = WebDriverFactory.getWebDriver();
        pageFactory = new PageFactory(webDriver);
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

    public PageFactory getPageFactory() {
        return pageFactory;
    }


    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setNewDepartmentName(String newDepartmentName) {
        this.newDepartmentName = newDepartmentName;
    }

    public String getNewDepartmentName() {
        return newDepartmentName;
    }
}
