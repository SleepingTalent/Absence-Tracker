@manager
Feature: Manager Features

  @wip
  Scenario: A Manager can Approve an Employees Annual Leave Request
    Given a valid "Manager" user with an annual leave request awaiting approval
    When the user logs in and selects authoriseLeave feature
    And approves the employees holiday
    Then employees holidays balance is updated according
    And the shared department calender is updated.

  @wip
  Scenario: A Manager can Decline an Employees Annual Leave Request
    Given a valid "Manager" user with an annual leave request awaiting approval
    When the user logs in and selects authoriseLeave feature
    And declines the employees holiday
    Then employees holidays balance is updated according
    And the shared department calender is updated.

  @wip
  Scenario: A Manager can view the Annual Leave Breakdown and Balances for a Department
    Given a valid "Manager" user
    When the user logs in and selects authoriseLeave feature
    And selects the "Department Statistics" feature
    Then a graph of the department annual leave breakdown and balance is displayed

  @wip
  Scenario: A Manager can enter an Absence for an Employee
    Given a valid "Manager" user
    When the user logs in
    And they select "absenceManagement" feature
    And they enter a absence for an employee from "monday lastweek" to "friday lastweek"
    Then absence waiting confirmation table is updated

  @wip
  Scenario: A Manager can enter an Absence for an Employee who had a holiday declined for that period
    Given a valid "Manager" user
    When the user logs in
    And they select "absenceManagement" feature
    And they enter a absence for an employee for a period where a holiday was previously declined
    Then absence waiting confirmation table is updated
    And a "" "" warngin message is displayed
    When they select the "ViewEmployeeAbsence" feature
    Then the employee absence history table is diplayed showing this absence coincided with a declined holiday

  @wip
  Scenario: A Manager can view the Absence Statistics for a Department
    Given a valid "Manager" user
    When the user logs in and selects absenceManagement feature
    And selects the "Department Statistics" feature
    Then a graph of the department absence statistics is displayed

