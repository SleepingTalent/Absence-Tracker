package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.db.entity.Employee;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.BrowseDepartmentsDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.BrowseEmployeesDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.CreateDepartmentDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.dialog.CreateEmployeeDialog;
import com.noveria.cukes.helpers.selenium.page.dashboard.menu.AdminMenu;
import com.noveria.cukes.helpers.selenium.page.dashboard.menu.FeaturesMenu;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import com.noveria.cukes.runtime.RuntimeState;

import static org.junit.Assert.assertEquals;

/**
 * Created by lynseymcgregor on 01/07/2015.
 */
public class AdminPage extends Page {

    public static final String ADMIN_FORM = "adminForm";

    private AdminMenu adminMenu;
    private FeaturesMenu featuresMenu;

    private CreateDepartmentDialog createDepartmentDialog;

    private CreateEmployeeDialog createEmployeeDialog;

    private BrowseDepartmentsDialog browseDepartmentsDialog;

    private BrowseEmployeesDialog browseEmployeesDialog;

    public AdminPage(CucumberWebDriver webDriver) {
        super(webDriver);

        adminMenu = new AdminMenu(webDriver);
        featuresMenu = new FeaturesMenu(webDriver);

        createDepartmentDialog = new CreateDepartmentDialog(webDriver);
        browseDepartmentsDialog = new BrowseDepartmentsDialog(webDriver);

        createEmployeeDialog = new CreateEmployeeDialog(webDriver);
        browseEmployeesDialog = new BrowseEmployeesDialog(webDriver);
    }

    public void assertPagePresent() {
        getPageHelper().findElementById(ADMIN_FORM);
    }

    public AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public FeaturesMenu getFeaturesMenu() {
        return featuresMenu;
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