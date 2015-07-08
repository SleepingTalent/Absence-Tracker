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

  @wip
  Scenario: An Employee can request a Holiday
    Given a valid "Employee" user
    When the user logs in
    And they select "myAnnualLeave" feature
    And they request a holiday from "today" to "tomorrow"
    Then a holiday request is created