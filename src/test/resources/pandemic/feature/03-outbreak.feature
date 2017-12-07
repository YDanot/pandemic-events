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