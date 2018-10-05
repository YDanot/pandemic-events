Feature: Disease cubes

  Scenario: Cube counter decrement on infection
    Given a minimalist game
    When Paris is infected by Blue
    Then There should be 23 blue cubes available

  Scenario: Cube counter doesn't decrement on fourth infection
    Given a minimalist game
    And New_york has already been infected by Blue 3 times
    When New_york is infected by Blue
    Then There should be 21 blue cubes available

  Scenario: Cube counter cannot reach 0
    Given a minimalist game
    And 23 blue cubes has been taken from bank
    When Paris is infected by Blue
    Then game should be lost

  Scenario: Cube counter increment on treatment
    Given a minimalist game
    And Paris has already been infected by Blue 2 times
    When Blue is treated in Paris
    Then There should be 23 blue cubes available

  Scenario: Cube counter increment on full treatment
    Given a minimalist game
    And Paris has already been infected by Blue 3 times
    And Blue has been cured
    When Blue is treated in Paris
    Then There should be 24 blue cubes available