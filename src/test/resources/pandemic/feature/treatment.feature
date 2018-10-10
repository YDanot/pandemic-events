Feature: Treatment

  Scenario: Treat an infected city
    Given a minimalist game
    And Paris has already been infected by Blue 2 times
    When Blue is treated in Paris
    Then Blue infection level of Paris should be 1

  Scenario: Remove all cubes when disease is cured
    Given a minimalist game
    And Paris has already been infected by Blue 3 times
    And Blue has been cured
    When Blue is treated in Paris
    Then Blue infection level of Paris should be 0

  Scenario: A player is able to treat a disease on his location
    Given a minimalist game
    And Paris has already been infected by Blue 2 times
    And Scientist is located at Paris
    When Scientist treats Blue
    Then Blue infection level of Paris should be 1

  Scenario: A player is not able to treat a disease on other location
    Given a minimalist game
    And Paris has already been infected by Blue 2 times
    And Scientist is located at New_York
    Then Scientist should not be able to treat Blue
    Then Blue infection level of Paris should stay at 2