Feature: Outbreaking

  Scenario: Game is lost on eighth outbreak
    Given the occident initial sub-network
    And Paris has already been infected by Blue 3 times
    And there already were 7 outbreaks
    When Paris is infected by Blue
    Then game should be lost

  Scenario: Game is lost on eighth outbreak
    Given the occident initial sub-network
    And Paris has already been infected by Blue 3 times
    And there already were 2 outbreaks
    When Paris is infected by Blue
    Then outbreak counter value should be 3