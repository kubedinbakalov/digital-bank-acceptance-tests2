Feature: login
  @test
  Scenario: Positive login
    Given the user is on the home page
    And the user populates the username field with "Steve@gmail.com"
    And the user populates the password field with "Test123$$"
    When the user clicks the sign-in button
    Then user should see user verify massage "Welcome Steven"
  @Smoke

  Scenario: Failed login with incorrect password
    Given the user is on the home page
    And the user populates the username field with "email@gmail.com"
    And the user populates the password field with an incorrect password "Email1228A"
    When the user clicks the sign-in button
    Then the user should see an error message "Error"

  Scenario: Failed login with non-existent user
    Given the user is on the home page
    And the user populates a non-existent username "email@gmail.comA"
    And the user populates the password field with "Email1228"
    When the user clicks the sign-in button
    Then the user should see an error message "Error"

  Scenario: Failed login with empty fields
    Given the user is on the home page
    When the user clicks the sign-in button
    Then the user should see an error message "Error"