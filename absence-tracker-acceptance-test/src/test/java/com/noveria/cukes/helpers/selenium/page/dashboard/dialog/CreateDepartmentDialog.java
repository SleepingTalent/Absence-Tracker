package com.noveria.cukes.helpers.selenium.page.dashboard.dialog;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;


/**
 * Created by lynseymcgregor on 09/06/2015.
 */
public class CreateDepartmentDialog extends Page {

    public static final String DEPARTMENT_NAME_FIELD = "tabPanel:addDepartment:deptName";
    public static final String CREATE_DEPARTMENT_BTN = "tabPanel:addDepartment:addDepartmentBtn";
    public static final String CREATE_DEPARTMENT_DIALOG_ID = "tabPanel:addDepartmentDialog";


    public CreateDepartmentDialog(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void assertDialogPresent(boolean reThrow) {
        getPageHelper().findElementById(CREATE_DEPARTMENT_DIALOG_ID,reThrow);
    }

    public void setName(String name) {
        try {
            getPageHelper().findElementById(DEPARTMENT_NAME_FIELD, true).sendKeys(name);
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findElementById(DEPARTMENT_NAME_FIELD, true).sendKeys(name);
        }
    }

    public void clickCreateBtn() {
        getPageHelper().findElementById(CREATE_DEPARTMENT_BTN).click();
    }
}
