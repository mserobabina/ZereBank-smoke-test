Feature: Account Activity functions
  Background:
    Given the user is on the login page
    And User logins with username "username" and password "password"

Scenario: Account activity features
  And the user navigates to "Account Activity" menu
  Then the Account Activity page should be displayed
