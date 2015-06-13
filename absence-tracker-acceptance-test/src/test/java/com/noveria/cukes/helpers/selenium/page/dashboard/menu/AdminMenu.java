package com.noveria.cukes.helpers.selenium.page.dashboard.menu;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class AdminMenu extends Page {

    public static final String CREATE_DEPARTMENT_MENU_ID = "tabPanel:CreateDept";
    public static final String BROWSE_DEPARTMENT_MENU_ID = "tabPanel:BrowseDept";

    public AdminMenu(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void openCreateMenu(boolean reThrow) {
            getPageHelper().findLinkByText("Create",reThrow).click();
    }

    public void openBrowseMenu(boolean reThrow) {
            getPageHelper().findLinkByText("Browse",reThrow).click();
    }

    public void clickOnCreateDepartment(boolean reThrow) {
        getPageHelper().findElementById(CREATE_DEPARTMENT_MENU_ID, reThrow).click();
    }

    public void clickOnBrowseDepartment(boolean reThrow) {
        getPageHelper().findElementById(BROWSE_DEPARTMENT_MENU_ID, reThrow).click();
    }

}
