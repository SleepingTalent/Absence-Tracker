@admin
Feature: Administrator Features

  Scenario: An Admin User Creates a Department without a name
    Given a valid "Admin" user
    When the user logs in
    And they create a Department without a name
    Then a "Department Name is required" "Department Name is required" validation error is displayed

  @james
  Scenario: An Admin User Creates a Department
    Given a valid "Admin" user
    When the user logs in
    And checks that the "Marketing" department does not exist
    And they create a Department called "Marketing"
    Then the "Marketing" Department is created