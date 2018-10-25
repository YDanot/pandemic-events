Feature: Dispatcher Role handling

  The Dispatcher may, as an action, either:
  * move any pawn, if its owner agrees, to any city containing another pawn
  * move another player’s pawn, if its owner agrees, as if it were his own.

  Scenario: Dispatcher can move any pawns to any city containing another pawn
    Given a standard game
    But Scientist, Dispatcher, Medic are playing
    And Scientist is located at Paris
    And Medic is located at Lima
    And it is the turn of Dispatcher
    And Scientist agreed to be moved by Dispatcher
    When He joins Medic and Scientist
    Then Scientist should be located at Lima

  Scenario: only the Dispatcher can move any pawns to any city containing another pawn
    Given a standard game
    But Scientist, Dispatcher, Medic are playing
    And Scientist is located at Paris
    And Medic is located at Lima
    And it is the turn of Medic
    Then he should not be able to joins Dispatcher and Scientist

  Scenario: Dispatcher can drive any pawns as if it were his own.
    Given a standard game
    But Dispatcher, Medic are playing
    And Medic is located at Lima
    And it is the turn of Dispatcher
    And Medic agreed to be moved by Dispatcher
    Then he should be able to make Medic drive to Santiago

  Scenario: Dispatcher can direct fly any pawns as if it were his own.
    Given a standard game
    But Scientist, Dispatcher are playing
    And Scientist is located at Paris
    And Dispatcher hand is Lima, Baghdad
    And it is the turn of Dispatcher
    And Scientist agreed to be moved by Dispatcher
    When he makes Scientist direct fly to Baghdad
    Then Scientist should be located at Baghdad
    And Dispatcher hand should be Lima
    And the player discard pile should contains Baghdad

  Scenario: Dispatcher can charter fly any pawns as if it were his own.
    Given a standard game
    But Scientist, Dispatcher are playing
    And Scientist is located at Paris
    And Dispatcher hand is Paris,Baghdad
    And it is the turn of Dispatcher
    And Scientist agreed to be moved by Dispatcher
    When he makes Scientist charter fly to Sydney
    Then Scientist should be located at Sydney
    And Dispatcher hand should be Baghdad
    And the player discard pile should contains Paris

  Scenario: Dispatcher can shuttle fly any pawns as if it were his own.
    Given a standard game
    But Scientist, Dispatcher are playing
    And a research station has been built on Paris
    And Scientist is located at Paris
    And it is the turn of Dispatcher
    And Scientist agreed to be moved by Dispatcher
    When he makes Scientist shuttle fly to Atlanta
    Then Scientist should be located at Atlanta

  Scenario: Dispatcher cannot make anyone move without his agreement.
    Given a standard game
    But Scientist, Dispatcher, Medic are playing
    And Scientist is located at Paris
    And Medic is located at Lima
    And it is the turn of Dispatcher
    When Scientist disagrees to be moved by Dispatcher
    Then he should not be able to joins Medic and Scientist
