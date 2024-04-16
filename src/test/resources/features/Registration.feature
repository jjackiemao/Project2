Feature: Registration


  Scenario: Successful registration
    Given #Date of birth
    And #First name
    And  #Last name
    And #Password, Confirm Password
    And #Email, Confirm Email
    And #Terms and condition
    And #Over 18, code of conduct
    When I press the join button
    Then I am registered