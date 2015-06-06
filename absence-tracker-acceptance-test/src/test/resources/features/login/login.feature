@login
Feature: Application Login Features

  Scenario: A valid Admin User logs in
    Given a valid "Admin" user
    When the user logs in
    Then they are redirected to the appropriate dashboard

  Scenario: A valid Manager User logs in
    Given a valid "Manager" user
    When the user logs in
    Then they are redirected to the appropriate dashboard

  Scenario: A valid Employee User logs in
    Given a valid "Employee" user
    When the user logs in
    Then they are redirected to the appropriate dashboard

  Scenario: An invalid User logs in
    Given an invalid user
    When the user logs in
    Then a login error is displayed

  Scenario: An valid User logs in without roles
    Given a valid "NoRole" user
    When the user logs in
    Then a login error is displayed

  @wip
  Scenario: An valid User logs in with an unknown role
    Given a valid "UnknownRole" user
    When the user logs in
    Then a login error is displayed

  Scenario: An valid User logs in and out
    Given a valid "Admin" user
    When the user logs in
    Then they are redirected to the appropriate dashboard
    When the user logs out
    Then they are redirected to the login page



