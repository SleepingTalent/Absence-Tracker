@admin
Feature: Administrator Features

  Scenario: An Admin User Creates a Department without a name
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create a Department without a name
    Then a "Department Name is required" "Department Name is required" validation error is displayed

  Scenario: An Admin User Creates a Department
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And checks that the "Marketing" department does not exist
    And they create a Department called "Marketing"
    Then the "Marketing" Department is created

  Scenario: An Admin User cannot creates a Department that already exists
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create a Department called "Software Development"
    Then a "Create Department Failed" "Software Development Already Exists" validation error is displayed

  Scenario: An Admin User Creates an Employee without a firstname
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an Employee without a "firstname"
    Then a "Firstname is required" "Firstname is required" validation error is displayed

  Scenario: An Admin User Creates an Employee without a lastname
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an Employee without a "lastname"
    Then a "Lastname is required" "Lastname is required" validation error is displayed

  Scenario: An Admin User Creates an Employee without a username
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an Employee without a "username"
    Then a "Username is required" "Username is required" validation error is displayed

  Scenario: An Admin User Creates an Employee without a password
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an Employee without a "password"
    Then a "Password is required" "Password is required" validation error is displayed

  @wip
  Scenario: An Admin User Creates an Employee with a username that already exists
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an Employee with a usernname that already exists
    Then a "Username is already in use" "Username is already in use" validation error is displayed


  Scenario: An Admin User Creates an Employee without a department
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an Employee without a "department"
    Then a "Please Select Department" "Please Select Department" validation error is displayed

  Scenario: An Admin User Creates an Employee
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create an "Employee"
    Then the Employee is created
    And the user logs out
    When the "Employee" logs in
    Then they have the expected Employee features
    When the Employee logs out
    Then they are redirected to the login page

  Scenario: An Admin User Creates a Manager
    Given a valid "Admin" user
    When the user logs in and selects admin features
    And they create a "Manager"
    Then the Manager is created
    And the user logs out
    When the "Manager" logs in
    Then they have the expected Manager features
    When the Manager logs out
    Then they are redirected to the login page
