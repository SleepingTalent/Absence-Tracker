package com.noveria.cukes.helpers.selenium.page.dashboard.menu;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

/**
 * Created by lynseymcgregor on 01/07/2015.
 */
public class FeaturesMenu extends Page {

    private static final String LOGOUT_ID = "logout";
    private static final String FEATURES_MENU_ID = "featuresMenu_button";
    private static final String SYSTEM_ADMIN_ID = "adminFeature";
    private static final String MY_ANNUAL_LEAVE_ID = "myAnnualLeave";
    private static final String MY_ABSENCE_ID = "myAbsence";
    private static final String AUTHORISE_LEAVE_ID = "authoriseLeave";
    private static final String ENTER_ABSENCE_ID = "enterAbsence";

    public FeaturesMenu(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void clickLogout() {
        selectFeaturesMenu();
        getPageHelper().findElementById(LOGOUT_ID).click();
    }

    public void clickSystemAdmin() {
        selectFeaturesMenu();
        getPageHelper().findElementById(SYSTEM_ADMIN_ID).click();
    }

    private void selectFeaturesMenu() {
        getPageHelper().findElementById(FEATURES_MENU_ID).click();
    }

    public void assertEmployeeFeaturesPresent() {
        selectFeaturesMenu();
        getPageHelper().findElementById(MY_ANNUAL_LEAVE_ID);
        getPageHelper().findElementById(MY_ABSENCE_ID);
    }

    public void assertAdminFeaturesPresent() {
        selectFeaturesMenu();
        getPageHelper().findElementById(SYSTEM_ADMIN_ID);
    }

    public void assertManagerFeaturesPresent() {
        selectFeaturesMenu();
        getPageHelper().findElementById(AUTHORISE_LEAVE_ID);
        getPageHelper().findElementById(ENTER_ABSENCE_ID);
    }

    public void clickMyAnnualLeave() {
        selectFeaturesMenu();
        getPageHelper().findElementById(MY_ANNUAL_LEAVE_ID).click();
    }
}
