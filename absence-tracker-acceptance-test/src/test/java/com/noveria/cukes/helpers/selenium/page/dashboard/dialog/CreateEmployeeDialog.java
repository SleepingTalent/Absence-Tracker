package com.noveria.cukes.helpers.selenium.page.dashboard.dialog;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by lynseymcgregor on 28/06/2015.
 */
public class CreateEmployeeDialog extends Page {

    public static final String CREATE_EMPLOYEE_DIALOG_ID = "tabPanel:addEmployeeDialog";

    public static final String EMPLOYEE_FIRSTNAME_FIELD = "tabPanel:addEmployee:firstName";
    public static final String EMPLOYEE_LASTNAME_FIELD = "tabPanel:addEmployee:lastName";
    public static final String EMPLOYEE_DEPARTMENT_FIELD = "tabPanel:addEmployee:department";
    public static final String EMPLOYEE_USERNAME_FIELD = "tabPanel:addEmployee:username";
    public static final String EMPLOYEE_PASSWORD_FIELD = "tabPanel:addEmployee:password";
    public static final String EMPLOYEE_MANAGER_ROLE_FIELD = "tabPanel:addEmployee:mangerrole";


    public static final String CREATE_DEPARTMENT_BTN = "tabPanel:addEmployee:addEmployeeBtn";

    public CreateEmployeeDialog(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void assertDialogPresent(boolean reThrow) {
        getPageHelper().findElementById(CREATE_EMPLOYEE_DIALOG_ID, reThrow);
    }

    public void setFirstName(String firstname) {
        sendTextToField(EMPLOYEE_FIRSTNAME_FIELD,firstname);
    }

    public void setLastName(String lastname) {
        sendTextToField(EMPLOYEE_LASTNAME_FIELD,lastname);
    }

    public void setDepartment(String department) {
        getPageHelper().findElementById(EMPLOYEE_DEPARTMENT_FIELD+"_label").click();

        List<WebElement> elements = getPageHelper().findElementsByClass("ui-selectonemenu-item");

        boolean found = false;
        for(WebElement element: elements) {
            if(element.getText().equalsIgnoreCase(department)) {
                element.click();
                found = true;
                break;
            }
        }

        if(!found) {
            fail(department+" : option not found!");
        }
    }

    public void setUserName(String username) {
        sendTextToField(EMPLOYEE_USERNAME_FIELD,username);
    }

    public void setPassword(String password) {
        sendTextToField(EMPLOYEE_PASSWORD_FIELD,password);
    }

    private void sendTextToField(String id, String text) {
        try {
            getPageHelper().findElementById(id, true).sendKeys(text);
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findElementById(id, true).sendKeys(text);
        }
    }

    public void clickCreateBtn() {
        getPageHelper().findElementById(CREATE_DEPARTMENT_BTN).click();
    }
}
