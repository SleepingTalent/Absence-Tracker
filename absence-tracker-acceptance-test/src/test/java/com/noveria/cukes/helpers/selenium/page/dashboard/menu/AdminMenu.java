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
        try {
            //getPageHelper().moveToAndClickElement(assertCreateMenuDisplayed(reThrow));
            getPageHelper().moveToElement(assertCreateMenuDisplayed(reThrow));
            assertCreateMenuDisplayed(reThrow).click();
        } catch (SeleniumTimeoutException wbe) {
            getPageHelper().moveToElement(assertCreateMenuDisplayed(reThrow));
            assertCreateMenuDisplayed(reThrow).click();
        }
    }

    public void openBrowseMenu(boolean reThrow) {
        try {
            //getPageHelper().moveToAndClickElement(assertBrowseMenuDisplayed(reThrow));
            getPageHelper().moveToElement(assertBrowseMenuDisplayed(reThrow));
            assertBrowseMenuDisplayed(reThrow).click();
        } catch (SeleniumTimeoutException wbe) {
            getPageHelper().moveToElement(assertBrowseMenuDisplayed(reThrow));
            assertBrowseMenuDisplayed(reThrow).click();
        }
    }

    private WebElement assertCreateMenuDisplayed(boolean reThrow) {
        return getPageHelper().assertMenuDisplayed("Create", reThrow);
    }

    private WebElement assertBrowseMenuDisplayed(boolean reThrow) {
        return getPageHelper().assertMenuDisplayed("Browse", reThrow);
    }

    public void clickOnCreateDepartment(boolean reThrow) {
        getPageHelper().findElementById(CREATE_DEPARTMENT_MENU_ID, reThrow).click();
    }

    public void clickOnBrowseDepartment(boolean reThrow) {
        getPageHelper().findElementById(BROWSE_DEPARTMENT_MENU_ID, reThrow).click();
    }

}
