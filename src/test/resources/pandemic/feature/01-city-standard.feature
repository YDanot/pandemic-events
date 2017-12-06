Feature: Standard Definition

  Scenario: Occident network definition

    Given the occident initial sub-network
    Then the cities should have the following infection levels:
      | city   | level |
      | Paris  | 0     |
      | London | 0     |
      | Essen  | 0     |
      | Alger  | 0     |
      | Madrid | 0     |
    And Paris should be linked to London, Essen, Milan, Alger, Madrid.