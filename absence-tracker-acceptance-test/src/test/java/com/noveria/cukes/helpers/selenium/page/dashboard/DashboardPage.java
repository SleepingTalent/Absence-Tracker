package com.noveria.cukes.helpers.selenium.page.dashboard;

import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.CreateDepartmentDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.menu.AdminMenu;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DashboardPage extends Page {

    private static final String ADMIN_TEXT_LABEL_ID  = "tabPanel:adminrole";
    private static final String MANAGER_TEXT_LABEL_ID  = "tabPanel:managerrole";
    private static final String EMPLOYEE_TEXT_LABEL_ID  = "tabPanel:employerole";
    private static final String LOGOUT_BTN_ID = "logoutBtn";
    public static final String DASHBOARD_PANEL = "dashboardPanel";

    private AdminMenu adminMenu;

    private CreateDepartmentDialog createDepartmentDialog;

    public DashboardPage(CucumberWebDriver webDriver) {
        super(webDriver);

        adminMenu = new AdminMenu(webDriver); ;
        createDepartmentDialog = new CreateDepartmentDialog(webDriver);
    }

    public void assertPagePresent() {
        getPageHelper().findElementById(DASHBOARD_PANEL);
    }

    public void assertAdminTextPresent(){
        String adminText = getPageHelper().findElementById(ADMIN_TEXT_LABEL_ID).getText();
        assertEquals("You have admin access!",adminText);
    }

    public void assertManagerTextPresent(){
        String adminText = getPageHelper().findElementById(MANAGER_TEXT_LABEL_ID).getText();
        assertEquals("You have manager access!",adminText);
    }

    public void assertEmployeeTextPresent(){
        String adminText = getPageHelper().findElementById(EMPLOYEE_TEXT_LABEL_ID).getText();
        assertEquals("You have employee access!",adminText);
    }

    public void clickLogoutBtn() {
        getPageHelper().findElementById(LOGOUT_BTN_ID).click();
    }

    public AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public CreateDepartmentDialog getCreateDepartmentDialog() {
        return createDepartmentDialog;
    }

    public void assertValidationErrorIsDisplayed(String title, String message) {
        assertValidationErrorDisplayed(title, message);
    }
}
