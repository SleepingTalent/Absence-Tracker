@login
Feature: Example Feature

  Scenario: A valid Admin User logs in
    Given a valid "Admin" user
    When the user logs in
    Then they are redirected to the appropriate dashboard

  Scenario: An invalid User logs in
    Given an invalid user
    When the user logs in
    Then a login error is displayed



