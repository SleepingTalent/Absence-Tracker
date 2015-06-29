package com.noveria.cukes.helpers.selenium.page.dashboard.dialog;

import com.noveria.cukes.helpers.db.entity.Employee;
import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

import static org.junit.Assert.fail;

/**
 * Created by lynseymcgregor on 29/06/2015.
 */
public class BrowseEmployeesDialog extends Page {

    public static final String BROWSE_EMPLOYEES_DIALOG_ID = "tabPanel:browseEmployeesDialog";

    //ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all
    public static final String CLOSE_ICON_CLASS = "";

    public BrowseEmployeesDialog(CucumberWebDriver webDriver) {
        super(webDriver);
    }


    public void closeDialog() {
        getPageHelper().findByLinkWithinDivById("tabPanel:browseEmployeesDialog",false).click();
    }

    public void assertDialogPresent(boolean reThrow) {
        getPageHelper().findElementById(BROWSE_EMPLOYEES_DIALOG_ID,reThrow);
    }

    public void assertDialogPresent() {

        getPageHelper().findElementById(BROWSE_EMPLOYEES_DIALOG_ID,false);
    }

    public void assertEmployeeNotPresent(Employee employee) {
        try {
            getPageHelper().findTableRowWithText(employee.getFullname(), true);
            fail(employee.getFullname()+" already exists!");
        } catch (SeleniumTimeoutException ste) {
            //Pass department not found!
        }
    }

    public void assertEmployeePresent(Employee employee) {
        try {
            getPageHelper().findTableRowWithText(employee.getFullname(), true);
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findTableRowWithText(employee.getFullname(), true);
        }
    }

}
