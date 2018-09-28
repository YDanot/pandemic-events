Feature: Outbreaking

  Scenario: Outbreak global counter increase on fourth infection
    Given the occident initial sub-network
    And Paris has already been infected by Blue 3 times
    When Paris is infected by Blue
    Then outbreak counter value should be 1

  Scenario: Outbreak propagation
    Given the occident initial sub-network
    And Paris has already been infected by Blue 3 times
    When Paris is infected by Blue
    Then the cities should have the following infection levels:
      | cityName | blue level |
      | London   | 1          |
      | Essen    | 1          |
      | Algiers  | 1          |
      | Madrid   | 1          |
      | Milan    | 1          |

  Scenario: No loop on consecutive outbreaks
    Given the occident initial sub-network
    And Paris has already been infected by Blue 3 times
    And London has already been infected by Blue 3 times
    And Essen has already been infected by Blue 2 times
    When Paris is infected by Blue
    Then outbreak counter value should be 3
    And the cities should have the following infection levels:
      | cityName | blue level |
      | Essen    | 3          |
      | Algiers  | 1          |
      | Madrid   | 2          |
      | Milan    | 2          |
