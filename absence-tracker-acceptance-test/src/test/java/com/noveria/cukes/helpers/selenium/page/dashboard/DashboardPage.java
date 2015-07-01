package com.noveria.cukes.helpers.selenium.page.dashboard;

import com.noveria.cukes.helpers.UserType;
import com.noveria.cukes.helpers.db.entity.Employee;
import com.noveria.cukes.helpers.selenium.page.LoginPage;
import com.noveria.cukes.helpers.selenium.page.Page;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.BrowseDepartmentsDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.BrowseEmployeesDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.CreateDepartmentDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.CreateEmployeeDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.menu.AdminMenu;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import com.noveria.cukes.runtime.RuntimeState;

import static org.junit.Assert.assertEquals;

public class DashboardPage extends Page {

    private static final String ADMIN_TEXT_LABEL_ID = "tabPanel:adminrole";
    private static final String MANAGER_TEXT_LABEL_ID = "tabPanel:managerrole";
    private static final String EMPLOYEE_TEXT_LABEL_ID = "tabPanel:employerole";
    private static final String LOGOUT_ID = "logout";
    private static final String FEATURES_MENU_ID = "featuresMenu_button";
    public static final String DASHBOARD_PANEL = "dashboardPanel";

    private AdminMenu adminMenu;

    private CreateDepartmentDialog createDepartmentDialog;

    private CreateEmployeeDialog createEmployeeDialog;

    private BrowseDepartmentsDialog browseDepartmentsDialog;

    private BrowseEmployeesDialog browseEmployeesDialog;

    public DashboardPage(CucumberWebDriver webDriver) {
        super(webDriver);

        adminMenu = new AdminMenu(webDriver);

        createDepartmentDialog = new CreateDepartmentDialog(webDriver);
        browseDepartmentsDialog = new BrowseDepartmentsDialog(webDriver);

        createEmployeeDialog = new CreateEmployeeDialog(webDriver);
        browseEmployeesDialog = new BrowseEmployeesDialog(webDriver);
    }

    public void assertPagePresent() {
        getPageHelper().findElementById(DASHBOARD_PANEL);
    }

    public void assertAdminTextPresent() {
        String adminText = getPageHelper().findElementById(ADMIN_TEXT_LABEL_ID).getText();
        assertEquals("You have admin access!", adminText);
    }

    public void assertManagerTextPresent() {
        String adminText = getPageHelper().findElementById(MANAGER_TEXT_LABEL_ID).getText();
        assertEquals("You have manager access!", adminText);
    }

    public void assertEmployeeTextPresent() {
        String adminText = getPageHelper().findElementById(EMPLOYEE_TEXT_LABEL_ID).getText();
        assertEquals("You have employee access!", adminText);
    }

    public void clickLogoutBtn() {
        getPageHelper().findElementById(FEATURES_MENU_ID).click();
        getPageHelper().findElementById(LOGOUT_ID).click();
    }

    public AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public CreateDepartmentDialog getCreateDepartmentDialog() {
        return createDepartmentDialog;
    }

    public CreateEmployeeDialog getCreateEmployeeDialog() {
        return createEmployeeDialog;
    }

    public BrowseEmployeesDialog getBrowseEmployeesDialog() {
        return browseEmployeesDialog;
    }

    public void assertValidationErrorIsDisplayed(String title, String message) {
        assertValidationErrorDisplayed(title, message);
    }

    public BrowseDepartmentsDialog getBrowseDepartmentsDialog() {
        return browseDepartmentsDialog;
    }

    public void checkThatTheDepartmentDoesNotExist(RuntimeState runtimeState, String departmentName) {
        openBrowseDepartmentsDialog();

        getBrowseDepartmentsDialog().assertDepartmentNotPresent(departmentName);
        runtimeState.takeScreenShot();

        getBrowseDepartmentsDialog().closeDialog();
    }

    public void createDepartmentWithName(RuntimeState runtimeState, String departmentName) {
        openCreateDepartmentDialog();

        getCreateDepartmentDialog().setName(departmentName);
        runtimeState.takeScreenShot();

        getCreateDepartmentDialog().clickCreateBtn();
    }

    public void checkThatTheDepartmentExist(RuntimeState runtimeState, String departmentName) {
        openBrowseDepartmentsDialog();

        getBrowseDepartmentsDialog().assertDepartmentPresent(departmentName);
        runtimeState.takeScreenShot();

        getBrowseDepartmentsDialog().closeDialog();
    }

    private void openCreateDepartmentDialog() {

        try {
            getAdminMenu().clickOnCreateDepartment();
            getCreateDepartmentDialog().assertDialogPresent(true);
        } catch (SeleniumTimeoutException ste) {
                getAdminMenu().clickOnCreateDepartment();
                getCreateDepartmentDialog().assertDialogPresent(false);
        }
    }

    private void openBrowseDepartmentsDialog() {

        try {
            getAdminMenu().clickOnBrowseDepartment();
            getBrowseDepartmentsDialog().assertDialogPresent(true);
        } catch (SeleniumTimeoutException ste) {
            getAdminMenu().clickOnBrowseDepartment();
            getBrowseDepartmentsDialog().assertDialogPresent();
        }
    }

    public void createEmployee(RuntimeState runtimeState, Employee employee) {
        openCreateEmployeeDialog();

        getCreateEmployeeDialog().setFirstName(employee.getFirstname());
        getCreateEmployeeDialog().setLastName(employee.getLastname());
        getCreateEmployeeDialog().setDepartment(employee.getDepartment());
        getCreateEmployeeDialog().setUserName(employee.getUsername());
        getCreateEmployeeDialog().setPassword(employee.getPassword());

        runtimeState.takeScreenShot();

        getCreateEmployeeDialog().clickCreateBtn();
    }

    private void openCreateEmployeeDialog() {
        try {
            getAdminMenu().clickOnCreateEmployee();
            getCreateEmployeeDialog().assertDialogPresent(true);
        } catch (SeleniumTimeoutException ste) {
            getAdminMenu().clickOnCreateEmployee();
            getCreateEmployeeDialog().assertDialogPresent(false);
        }
    }

    public void checkThatTheEmployeeExist(RuntimeState runtimeState, Employee employee) {
        openBrowseEmployeesDialog();

        getBrowseEmployeesDialog().assertEmployeePresent(employee);
        runtimeState.takeScreenShot();

        getBrowseEmployeesDialog().closeDialog();
    }

    private void openBrowseEmployeesDialog() {

        try {
            getAdminMenu().clickOnBrowseEmployees();
            getBrowseEmployeesDialog().assertDialogPresent(true);
        } catch (SeleniumTimeoutException ste) {
            getAdminMenu().clickOnBrowseEmployees();
            getBrowseEmployeesDialog().assertDialogPresent();
        }
    }
}
