package com.noveria.cukes.helpers.selenium.page.dashboard.menu;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class AdminMenu extends Page {

    public static final String CREATE_DEPARTMENT_MENU_ID = "CreateDept";
    public static final String CREATE_EMPLOYEE_MENU_ID = "CreateEmployee";

    public static final String BROWSE_DEPARTMENT_MENU_ID = "BrowseDept";
    public static final String BROWSE_EMPLOYEES_MENU_ID = "BrowseEmployee";
    public static final String MENU_ITEM_CLASS = "ui-menuitem-text";

    public AdminMenu(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnCreateDepartment() {
        selectCreateMenu();
        getPageHelper().findElementById(CREATE_DEPARTMENT_MENU_ID).click();
    }

    public void clickOnBrowseDepartment() {
        selectBrowseMenu();
        getPageHelper().findElementById(BROWSE_DEPARTMENT_MENU_ID).click();
    }

    public void clickOnCreateEmployee() {
        selectCreateMenu();
        getPageHelper().findElementById(CREATE_EMPLOYEE_MENU_ID).click();
    }

    public void clickOnBrowseEmployees() {
        try {
            selectBrowseMenu();
            getPageHelper().findElementById(BROWSE_EMPLOYEES_MENU_ID, true).click();
        } catch (SeleniumTimeoutException ste) {
            selectBrowseMenu();
            getPageHelper().findElementById(BROWSE_EMPLOYEES_MENU_ID, false).click();
        }
    }

    private void selectCreateMenu() {
        selectMenu(getPageHelper().findElementByClassAndText(MENU_ITEM_CLASS,"Create"));
    }

    private void selectBrowseMenu() {
        selectMenu(getPageHelper().findElementByClassAndText(MENU_ITEM_CLASS,"Browse"));
    }

    private void selectMenu(WebElement element) {
        getPageHelper().moveToElement(element);

    }
}
