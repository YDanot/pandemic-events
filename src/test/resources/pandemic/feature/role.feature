Feature: Role handling

  The Dispatcher may, as an action, either:
  • move any pawn, if its owner agrees, to any city
  containing another pawn, or
  • move another player’s pawn, if its owner agrees,
  as if it were his own.

  Scenario: Dispatcher can move any pawns to any city containing another pawn
    Given a standard game
    But Scientist, Dispatcher, Medic are playing
    And Scientist is located at Paris
    And Medic is located at Lima
    And it is the turn of Dispatcher
    When He joins Medic and Scientist
    Then Scientist should be located at Lima

  Scenario: only the Dispatcher can move any pawns to any city containing another pawn
    Given a standard game
    But Scientist, Dispatcher, Medic are playing
    And Scientist is located at Paris
    And Medic is located at Lima
    And it is the turn of Medic
    Then he should not be able to joins Dispatcher and Scientist