Feature: Treatment

  Scenario: Treat an infected city
    Given the occident initial sub-network
    And Paris has already been infected by Blue 2 times
    When Blue is treated in Paris
    Then Blue infection level of Paris should be 1