Feature: Treatment

  Scenario: A player is able to treat a disease on his location
    Given a standard game
    And Paris has already been infected by Blue 2 times
    And Scientist is located at Paris
    And it is the turn of Scientist
    When Scientist treats Blue
    Then Blue infection level of Paris should be 1

  Scenario: Remove all cubes when disease is cured
    Given a standard game
    And Paris has already been infected by Blue 3 times
    And Scientist is located at Paris
    And Blue has been cured
    And it is the turn of Scientist
    When Scientist treats Blue
    Then Blue infection level of Paris should be 0

  Scenario: A player is not able to treat a disease on other location
    Given a standard game
    And Paris has already been infected by Blue 2 times
    And Scientist is located at New_York
    And it is the turn of Scientist
    Then Scientist should not be able to treat Blue
    Then Blue infection level of Paris should stay at 2

  Scenario: Treatability of an infected city
    Given a standard game
    And Paris has already been infected by Blue 2 times
    And Scientist is located at Paris
    And it is the turn of Scientist
    Then Scientist should be able to treat Blue disease