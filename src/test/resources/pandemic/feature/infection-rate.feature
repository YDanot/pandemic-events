Feature: infection rate indicator

  Scenario: Move the Infection Rate Indicator up by one on the Infection Rate Track on the board
    Given the occident initial sub-network
    And the infection rate indicator is placed on 1st rate
    When we increase the infection rate
    Then the infection rate should be 2