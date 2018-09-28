Feature: Infection Cards

  Scenario: A city should be infected when infection cards is drawn
    Given the occident initial sub-network
    When Paris infection cards is drawn
    Then Blue infection level of Paris should be 1