Feature: Validate different login credentials on the Sauce Demo login page

  Background: Test Sauce Login Page Navigation
    Given user opens "Chrome" and Navigates to "Sauce Login" page
    And user logs in as "Admin" with "Valid Password"

@SmokeTest
  Scenario Outline: Checks different login credentials on the Sauce Demo login page
    When users clicks the "login" button
    #Scenario 2
    Then user is redirected to the Dashboard page
    #Scenario 3
    When users clicks the "menu" button
    #Scenario 4
    And users clicks the "logout" button

    #Scenario 5
    When user logs in as "<Username>" with "Valid Password"
    And users clicks the "login" button
    Then user sees the error message "<ErrorMessage>"

    Examples:
      | Username           |  ErrorMessage                                                              | Scenario  |
      | LockedOutUser      | Epic sadface: Sorry, this user has been locked out.                        | Scenario 5|
      | ProblemUser        | Epic sadface: Username and password do not match any user in this service  | Scenario 6|




