package com.noveria.cukes.helpers.selenium.page.dashboard.dialog;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;


/**
 * Created by lynseymcgregor on 09/06/2015.
 */
public class CreateDepartmentDialog extends Page {

    public static final String DEPARTMENT_NAME_FIELD = "tabPanel:addDepartment:deptName";
    public static final String CREATE_DEPARTMENT_BTN = "tabPanel:addDepartment:addDepartmentBtn";

    public CreateDepartmentDialog(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void setName(String name) {
        getPageHelper().findElementById(DEPARTMENT_NAME_FIELD).sendKeys(name);
    }

    public void clickCreateBtn() {
        getPageHelper().findElementById(CREATE_DEPARTMENT_BTN).click();
    }
}
