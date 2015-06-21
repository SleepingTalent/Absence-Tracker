package com.noveria.cukes.helpers.selenium.page.dashboard.menu;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class AdminMenu extends Page {

    public static final String CREATE_DEPARTMENT_MENU_ID = "tabPanel:CreateDept";
    public static final String CREATE_MENU_DIV = "tabPanel:adminMenu_createMenu";

    public static final String BROWSE_MENU_DIV = "tabPanel:adminMenu_browseMenu";
    public static final String BROWSE_DEPARTMENT_MENU_ID = "tabPanel:BrowseDept";

    public AdminMenu(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void openCreateMenu() {
            getPageHelper().findLinkByText("Create").click();
    }

    public void openBrowseMenu() {
            getPageHelper().findLinkByText("Browse").click();
    }

    public void clickOnCreateDepartment() {

        try {
            getPageHelper().findElementById(CREATE_MENU_DIV,true);
            getPageHelper().findElementById(CREATE_DEPARTMENT_MENU_ID).click();
        } catch (SeleniumTimeoutException ste) {
            openCreateMenu();
            getPageHelper().findElementById(CREATE_MENU_DIV);
            getPageHelper().findElementById(CREATE_DEPARTMENT_MENU_ID).click();
         }
    }

    public void clickOnBrowseDepartment() {

        try {
            getPageHelper().findElementById(BROWSE_MENU_DIV,true);
            getPageHelper().findElementById(BROWSE_DEPARTMENT_MENU_ID).click();
        } catch (SeleniumTimeoutException ste) {
            openBrowseMenu();
            getPageHelper().findElementById(BROWSE_MENU_DIV);
            getPageHelper().findElementById(BROWSE_DEPARTMENT_MENU_ID).click();
        }
    }

}
