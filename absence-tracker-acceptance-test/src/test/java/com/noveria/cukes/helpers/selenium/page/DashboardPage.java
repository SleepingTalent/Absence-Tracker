package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

import static org.junit.Assert.assertEquals;

public class DashboardPage extends Page {

    private static final String ADMIN_TEXT_LABEL_ID  = "adminrole";

    public DashboardPage(CucumberWebDriver webDriver) {

        super(webDriver);
    }

    public void assertAdminTextPresent(){
        String adminText = getPageHelper().findElementById(ADMIN_TEXT_LABEL_ID).getText();
        assertEquals("You have admin access!",adminText);
    }

}
