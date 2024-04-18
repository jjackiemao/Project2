Feature: Register Membership

  Scenario Outline: Membership registration
    Given I have entered my date of birth
    And I have entered my first name "<FirstName>"
    And I have entered my last name "<LastName>"
    And I have entered my email "<Email>"
    And I have confirmed my email "<Email>"
    And I have entered my password "<Password>"
    And I have confirmed my password "<ConfirmPassword>"
    And I have checked the "Terms and Conditions" and "Over 18" and "Code of Conduct" for example <ExampleIndex>
    When I press the join button
    Then I should see "<Outcome>"

    Examples:
      | FirstName | LastName | Email                         | Password  | ConfirmPassword | ExampleIndex | Outcome                                                          |
      | R         | P        | RP<RandomNumber>@email.com    | password1 | password1       | 1            | I should see a success message                                    |
      | J         |          | RP<RandomNumber>@email.com    | password2 | password2       | 2            | I should see an error message                                     |
      | K         | J        | RP<RandomNumber>@email.com    | password3 | password1       | 3            | The registration was not successful                               |
      | L         | M        | RP<RandomNumber>@email.com    | password4 | password4       | 4            | The registration has failed for not accepting the terms and conditions |
