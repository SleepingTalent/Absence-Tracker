package com.noveria.cukes.helpers.selenium.page.dashboard.dialog;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

import static org.junit.Assert.fail;

/**
 * Created by lynseymcgregor on 12/06/2015.
 */
public class BrowseDepartmentsDialog extends Page {

    public static final String BROWSE_DEPARTMENT_DIALOG_ID = "tabPanel:browseDepartmentsDialog";

    //ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all
    public static final String CLOSE_ICON_CLASS = "";

    public BrowseDepartmentsDialog(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void closeDialog() {
        getPageHelper().findByLinkWithinDivById("tabPanel:browseDepartmentsDialog",false).click();
    }

    public void assertDialogPresent(boolean reThrow) {
        getPageHelper().findElementById(BROWSE_DEPARTMENT_DIALOG_ID,reThrow);
    }

    public void assertDepartmentNotPresent(String departmentName) {
        try {
            getPageHelper().findTableRowWithText(departmentName, true);
            fail(departmentName+" already exists!");
        } catch (SeleniumTimeoutException ste) {
            //Pass department not found!
        }
    }

    public void assertDepartmentPresent(String departmentName) {
        try {
            getPageHelper().findTableRowWithText(departmentName, true);
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findTableRowWithText(departmentName, true);
        }
    }
}
