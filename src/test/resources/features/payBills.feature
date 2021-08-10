Feature: Pay Bills Functionality

  Background:
    Given the user is on the login page
    And User logins with username "username" and password "password"

Scenario: Date Format
  When Learn Date and DateFormat
@wip
  Scenario: Pay Bills Successful payment
  And the user navigates to "Pay Bills" menu
  Then the Pay Bills page should be displayed
  When user completes a successful Pay operation
  Then "The payment was successfully submitted." alert should be displayed
