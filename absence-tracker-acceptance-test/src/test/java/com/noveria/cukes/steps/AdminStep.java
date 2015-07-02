package com.noveria.cukes.steps;

import com.noveria.cukes.helpers.DepartmentType;
import com.noveria.cukes.helpers.db.DBHelper;
import com.noveria.cukes.helpers.db.entity.Employee;
import com.noveria.cukes.helpers.selenium.page.AdminPage;
import com.noveria.cukes.helpers.selenium.page.HolidayManagementPage;
import com.noveria.cukes.helpers.selenium.page.dashboard.WelcomePage;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created by lynseymcgregor on 09/06/2015.
 */
@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class AdminStep {

    Logger logger = LoggerFactory.getLogger(SetUpAndTearDownStep.class);

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    DBHelper dbHelper;

    @And("^they create a Department without a name$")
    public void they_create_a_without_a() throws Throwable {

        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();
        adminPage.createDepartmentWithName(runtimeState, "");
    }

    @Then("^a \"([^\"]*)\" \"([^\"]*)\" validation error is displayed$")
    public void a_validation_error_is_displayed(String title, String message) throws Throwable {
        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();
        adminPage.assertValidationErrorIsDisplayed(title, message);
        runtimeState.takeScreenShot();
    }

    @And("^checks that the \"([^\"]*)\" department does not exist$")
    public void checks_that_the_department_does_not_exist(String departmentName) throws Throwable {

        assertFalse(departmentName+" already exists!",dbHelper.departmentExists(departmentName));
        runtimeState.setNewDepartmentName(departmentName);
    }

    @And("^they create a Department called \"([^\"]*)\"$")
    public void they_create_a_Department_called(String departmentName) throws Throwable {
        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();

        adminPage.createDepartmentWithName(runtimeState, departmentName);
    }

    @Then("^the \"([^\"]*)\" Department is created$")
    public void the_Department_is_created(String departmentName) throws Throwable {
        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();
        adminPage.checkThatTheDepartmentExist(runtimeState, departmentName);
    }

    @And("^the \"([^\"]*)\" Department is already created$")
    public void the_Department_is_already_created(String departmentName) throws Throwable {
        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();
        adminPage.checkThatTheDepartmentExist(runtimeState, departmentName);
    }

    @And("^they create an Employee without a \"([^\"]*)\"$")
    public void they_create_an_Employee_without_a(String missingfield) throws Throwable {
        Employee employee = new Employee();

        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();

        if(missingfield.equalsIgnoreCase("firstname")) {
            employee.setFirstname("");
        }else if(missingfield.equalsIgnoreCase("lastname")) {
            employee.setLastname("");
        }else if(missingfield.equalsIgnoreCase("username")) {
            employee.setUsername("");
        }else if(missingfield.equalsIgnoreCase("password")) {
            employee.setPassword("");
        }else if(missingfield.equalsIgnoreCase("department")) {
            employee.setDepartment(DepartmentType.NO_SELECTION.getName());
        }

        adminPage.createEmployee(runtimeState, employee);
    }

    @Then("^the (?:Employee|Manager) is created$")
    public void the_Employee_is_created() throws Throwable {
        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();
        adminPage.checkThatTheEmployeeExist(runtimeState, runtimeState.getEmployee());
    }

    @And("^they create (?:an|a) \"([^\"]*)\"$")
    public void they_create_an(String employeeType) throws Throwable {
        Employee employee = new Employee();

        AdminPage adminPage = runtimeState.getPageFactory().getAdminPage();
        adminPage.assertPagePresent();

        if(employeeType.equalsIgnoreCase("Manager")) {
            employee.setManager(true);
        }

        runtimeState.setEmployee(employee);

        adminPage.createEmployee(runtimeState, runtimeState.getEmployee());
    }

    @And("^they select \"([^\"]*)\" feature$")
    public void they_select_feature(String feature) throws Throwable {

        WelcomePage welcomePage = runtimeState.getPageFactory().getWelcomePage();
        welcomePage.assertPagePresent();
        runtimeState.takeScreenShot();

        if(feature.equalsIgnoreCase("myAnnualLeave")){
            welcomePage.getFeaturesMenu().clickMyAnnualLeave();
        }else {
           fail(feature+" not supported!");
        }

    }

    @Then("^the holiday balance is set$")
    public void the_holiday_balance_is_set() throws Throwable {
        HolidayManagementPage holidayManagementPage =
                runtimeState.getPageFactory().getHolidayManagementPage();

        holidayManagementPage.assertDefaultHolidayTotalAllowance();
        holidayManagementPage.assertDefaultHolidayUsedAllowance();
        holidayManagementPage.assertDefaultHolidayRemainingAllowance();

        runtimeState.takeScreenShot();
    }
}
