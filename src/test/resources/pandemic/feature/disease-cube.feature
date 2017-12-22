Feature: Disease cubes

  Scenario: Cube counter decrement on infection
    Given the occident initial sub-network
    When Paris is infected by Blue
    Then There should be 39 blue cubes available