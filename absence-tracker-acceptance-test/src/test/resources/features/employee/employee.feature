@employee
Feature: Employee Features

  Scenario: Holiday Balance is set as expected for a new Employee
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an "Employee"
    Then the Employee is created
    And the user logs out
    When the "Employee" logs in
    And they select "myAnnualLeave" feature
    Then the holiday balance is set
    When the Employee logs out
    Then they are redirected to the login page