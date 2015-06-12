package com.noveria.cukes.helpers.selenium.page.dashboard.menu;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class AdminMenu extends Page {

    public static final String CREATE_DEPARTMENT_MENU_ID = "tabPanel:CreateDept";
    public static final String BROWSE_DEPARTMENT_MENU_ID = "tabPanel:BrowseDept";


    public AdminMenu(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void openCreateMenu() {
        getPageHelper().moveToElement(assertCreateMenuDisplayed());
        assertCreateMenuDisplayed().click();
    }

    public void openBrowseMenu() {
        getPageHelper().moveToElement(assertBrowseMenuDisplayed());
        assertBrowseMenuDisplayed().click();
    }

    private WebElement assertCreateMenuDisplayed() {
        return getPageHelper().assertMenuDisplayed("Create");
    }

    private WebElement assertBrowseMenuDisplayed() {
        return getPageHelper().assertMenuDisplayed("Browse");
    }

    public void clickOnCreateDepartment() {
        getPageHelper().findElementById(CREATE_DEPARTMENT_MENU_ID).click();
    }

    public void clickOnBrowseDepartment() {
        getPageHelper().findElementById(BROWSE_DEPARTMENT_MENU_ID).click();
    }

}
