Feature: Register Membership

  Scenario Outline: Membership registration
    Given I have entered my date of birth
    And I have entered my first name "<FirstName>"
    And I have entered my last name "<LastName>"
    And I have entered my email "<Email>"
    And I have confirmed my email "<Email>"
    And I have entered my password "<Password>"
    And I have confirmed my password "<Password>"
    And I have checked the Terms and Conditions
    And I have checked "Over 18" and "Code of Conduct"
    When I press the join button
    Then I should see <Outcome>

    Examples:
      | FirstName | LastName | Email               | Password   | Outcome                                       |
      | R         | P        | RP<RandomNumber>@example.com | password1  | "I should see a success message"          |
      | J         |          | RP<RandomNumber>@example.com  | password2  | "I should see an error message"                  |
      | K         | J        | RP<RandomNumber>@example.com | password3  | "The registration was not successful"                  |
      | L         | M        | RP<RandomNumber>@example.com | password4  | "The registration has failed for not accepting the terms and conditions" |
