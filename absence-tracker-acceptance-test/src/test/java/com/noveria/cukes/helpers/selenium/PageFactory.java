package com.noveria.cukes.helpers.selenium;

import com.noveria.cukes.helpers.selenium.page.AdminPage;
import com.noveria.cukes.helpers.selenium.page.HolidayManagementPage;
import com.noveria.cukes.helpers.selenium.page.LoginPage;
import com.noveria.cukes.helpers.selenium.page.dashboard.WelcomePage;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
public class PageFactory {

    private CucumberWebDriver webDriver;

    public PageFactory(CucumberWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public WelcomePage getWelcomePage() {
        return new WelcomePage(webDriver);
    }

    public AdminPage getAdminPage() {
        return new AdminPage(webDriver);
    }

    public HolidayManagementPage getHolidayManagementPage() {
        return new HolidayManagementPage(webDriver);
    }
}
