Feature: Account Activity functions
  Background:
    Given the user is on the login page
    And User logins with username "username" and password "password"
@wip
Scenario: Account activity features
  And the user navigates to "Account Activity" menu
  Then the Account Activity page should be displayed
  And Account drop down should have "Savings" selected
  Then Account drop down should have the following options
    | Savings     |
    | Checking    |
    | Savings     |
    | Loan        |
    | Credit Card |
    | Brokerage   |
  And Transactions table should have column names
    | Date        |
    | Description |
    | Deposit     |
    | Withdrawal  |
