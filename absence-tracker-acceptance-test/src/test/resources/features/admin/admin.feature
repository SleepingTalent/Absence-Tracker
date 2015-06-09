@admin
Feature: Administrator Features

  Scenario: A valid Admin User logs in
    Given a valid "Admin" user
    When the user logs in
    And they create a "department" without a "name"
    Then a "Department Name is required" "Department Name is required" validation error is displayed