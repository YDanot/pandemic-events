Feature: City Infection

  Scenario: First Infection

    Given the occident initial sub-network
    When Paris is infected by Blue
    Then Blue infection level of Paris should be 1

  Scenario: Fourth Infection
    Given the occident initial sub-network
    And Paris has already been infected by Blue 3 times
    When Paris is infected by Blue
    Then Blue infection level of Paris should stay at 3

  Scenario: 2 diseases infection
    Given the occident initial sub-network
    And Paris has already been infected by Blue 2 times
    When Paris is infected by Black
    Then Blue infection level of Paris should stay at 2
    And Black infection level of Paris should be 1