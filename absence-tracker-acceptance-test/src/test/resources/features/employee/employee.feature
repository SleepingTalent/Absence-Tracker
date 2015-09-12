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
  Scenario: An Employee can View a Graphical Representation of Annual Leave Balance
    Given a valid "Employee" user
    When the user logs in
    And they select "myAnnualLeave" feature
    And they select "myAnnualLeaveStatistics" feature
    Then a graph of the employees annual leave is displayed

  @wip
  Scenario: An Employee can request a Holiday
    Given a valid "Employee" user
    When the user logs in
    And they select "myAnnualLeave" feature
    And they request a holiday from "monday nextweek" to "friday nextweel"
    Then a holiday request is created

  @wip
  Scenario: An Employee cannot request a Holiday that exceeds their balance
    Given a valid "Employee" user
    When the user logs in
    And they select "myAnnualLeave" feature
    And they request a holiday that exceeds their curret balance
    Then a holiday request is not created
    And a "Holiday Request Failed" "Insufficient Holiday Allowance" validation error is displayed

  @wip
  Scenario: An Employee can View a Graphical Representation of Annual Leave Balance
    Given a valid "Employee" user
    When the user logs in
    And they select "myAnnualLeaveStatistics" feature
    Then a graph of the employees annual leave is displayed

  @wip
  Scenario: An Employee can confirm a period of Absence
    Given a valid "Employee" user with an outstanding absence awaiting confirmation
    When the user logs in
    And they select "myAbsence" feature
    And confirm the absence with the reason of "cold/flu"
    Then the absence history has now been updated

  @wip
  Scenario: An Employee can View a Graphical Representation of Absences
    Given a valid "Employee"
    When the user logs in
    And they select "myAbsence" feature
    And they select "myAbsenceStatistics" feature
    Then a graph of the employees absences is displayed
