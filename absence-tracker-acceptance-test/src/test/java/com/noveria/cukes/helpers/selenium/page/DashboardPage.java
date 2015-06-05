package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

import static org.junit.Assert.assertEquals;

public class DashboardPage extends Page {

    private static final String ADMIN_TEXT_LABEL_ID  = "tabPanel:adminrole";
    private static final String MANAGER_TEXT_LABEL_ID  = "tabPanel:managerrole";
    private static final String EMPLOYEE_TEXT_LABEL_ID  = "tabPanel:employerole";
    private static final String LOGOUT_BTN_ID = "mainPanel:logoutBtn";

    public DashboardPage(CucumberWebDriver webDriver) {

        super(webDriver);
    }

    public void assertAdminTextPresent(){
        String adminText = getPageHelper().findElementById(ADMIN_TEXT_LABEL_ID).getText();
        assertEquals("You have admin access!",adminText);
    }

    public void assertManagerTextPresent(){
        String adminText = getPageHelper().findElementById(MANAGER_TEXT_LABEL_ID).getText();
        assertEquals("You have manager access!",adminText);
    }

    public void assertEmployeeTextPresent(){
        String adminText = getPageHelper().findElementById(EMPLOYEE_TEXT_LABEL_ID).getText();
        assertEquals("You have employee access!",adminText);
    }

    public void clickLogoutBtn() {
        getPageHelper().findElementById(LOGOUT_BTN_ID).click();
    }
}
