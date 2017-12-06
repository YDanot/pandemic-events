Feature: Simple Infection

  Scenario: First Infection

    Given the occident initial sub-network
    When Paris is infected
    Then infection level of Paris should be 1

  Scenario: Fourth Infection
    Given the occident initial sub-network
    And Paris has already been infected 3 times
    When Paris is infected
    Then infection level of Paris should stay at 3