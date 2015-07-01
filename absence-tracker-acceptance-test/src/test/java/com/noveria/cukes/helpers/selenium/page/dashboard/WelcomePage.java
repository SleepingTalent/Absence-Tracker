package com.noveria.cukes.helpers.selenium.page.dashboard;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.dashboard.menu.FeaturesMenu;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

public class WelcomePage extends Page {

    public static final String DASHBOARD_PANEL = "welcomePanel";

    private FeaturesMenu featuresMenu;

    public WelcomePage(CucumberWebDriver webDriver) {
        super(webDriver);
        featuresMenu = new FeaturesMenu(webDriver);
    }

    public void assertPagePresent() {
        getPageHelper().findElementById(DASHBOARD_PANEL);
    }

    public FeaturesMenu getFeaturesMenu() {return featuresMenu;}

}
