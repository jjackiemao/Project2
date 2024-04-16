Feature: RegisterMembership

  Scenario: Successful membership registration
    Given I have entered date of birth
    And I have entered first name "R"
    And I have entered last name "P"
    And I have entered Email and confirmed Email "RP + randomNumber + @email.com"
    And I have entered password and confirmed password
    And I have checked Terms and condition
    And I have entered checked Over 18 and code of conduct
    When I press the join button
    Then I am registered

  Scenario: Failed membership registration (Missing surname)
    Given I have entered my date of birth
    And I have entered my first name "J"
    And I have left last name empty
    And I have entered my Email and confirmed the Email
    And I have entered my password and confirmed password
    And I have checked the Terms and condition
    And I have checked that i am over 18 and code of conduct
    When I press the join button to register
    Then The registration has failed