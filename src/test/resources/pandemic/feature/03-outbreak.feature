Feature: Outbreaking

  Scenario: Outbreak global counter increase on fourth infection
    Given the occident initial sub-network
    And Paris has already been infected 3 times
    When Paris is infected
    Then outbreak counter value should be 1

  Scenario: Outbreak propagation
    Given the occident initial sub-network
    And Paris has already been infected 3 times
    When Paris is infected
    Then the cities should have the following infection levels:
      | cityName | level |
      | London   | 1     |
      | Essen    | 1     |
      | Alger    | 1     |
      | Madrid   | 1     |
      | Milan    | 1     |

  Scenario: No loop on consecutive outbreaks
    Given the occident initial sub-network
    And Paris has already been infected 3 times
    And London has already been infected 3 times
    And Essen has already been infected 2 times
    When Paris is infected
    Then outbreak counter value should be 3
    And the cities should have the following infection levels:
      | cityName | level |
      | Essen    | 3     |
      | Alger    | 1     |
      | Madrid   | 2     |
      | Milan    | 2     |
