Feature: Register Membership

  Scenario Outline: Membership registration
    Given I have entered my date of birth
    And I have entered my first name "<FirstName>"
    And I have entered my last name "<LastName>"
    And I have entered my email "<Email>"
    And I have confirmed my email "<Email>"
    And I have entered my password "<Password>"
    And I have confirmed my password "<ConfirmPassword>"
    And I have checked the "Terms and Conditions" and "Over 18" and "Code of Conduct" for example <Scenario>
    When I press the join button
    Then I should see "<Outcome>"

    Examples:
      | FirstName | LastName | Email                         | Password  | ConfirmPassword | Scenario | Outcome                                                                |
      | R         | P        | RP<RandomNumber>@email.com    | password1 | password1       | 1            | The registration was successful!                                       |
      | R         |          | RP<RandomNumber>@email.com    | password2 | password2       | 2            | The registration was not successful (Last Name is required)!           |
      | R         | P        | RP<RandomNumber>@email.com    | password3 | password0       | 3            | The registration was not successful (Password did not match)!          |
      | R         | P        | RP<RandomNumber>@email.com    | password4 | password4       | 4            | The registration has failed for not accepting the terms and conditions |
