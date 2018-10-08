Feature: Basics Action

  Drive (or Ferry)
  Move your pawn to an adjacent city. Cities are adjacent if they are connected by a red line. Red lines that go off the edge of the board “wrap around” to the opposite board edge and continue to the indicated city. (For example, Sydney and Los Angeles are considered to be adjacent)

  Shuttle Flight
  If your pawn is in a city with a Research Station, move it to any other city with a Research Station.

  Scenario: Drive (or Ferry) on an adjacent city
    Given a minimalist game
    And Scientist is located at Paris
    Then Scientist should be able to drive to London, Essen, Milan, Algiers, Madrid

  Scenario: Drive (or Ferry) on a non adjacent city
    Given a minimalist game
    And Scientist is located at New_york
    Then Scientist should not be able to drive to Algiers

  Scenario: Shuttle Flight with both research station on location and destination
    Given a minimalist game
    And a research station has been built on New_York, Essen
    And Scientist is located at New_York
    Then Scientist should be able to shuttle flight to Essen

  Scenario: Shuttle Flight without research stations on location
    Given a minimalist game
    And a research station has been built on New_York, Essen
    And Scientist is located at Algiers
    Then Scientist should not be able to shuttle flight to New_York

  Scenario: Shuttle Flight without research stations on destination
    Given a minimalist game
    And a research station has been built on Essen
    And Scientist is located at Essen
    Then Scientist should not be able to shuttle flight to New_York

 #Direct Flight
 #Play a card from your hand and move your pawn to the pictured city. Discard the card to the Player Discard pile.

  Scenario: Direct Flight
    Given a minimalist game
    And Scientist is located at Essen
    And Scientist hand is New_York,Algiers
    When Scientist direct flies to New_York
    Then Scientist should be located at New_York
    And Scientist hand should be Algiers
    And the player discard pile should contains New_York

  Scenario: Direct Flight
    Given a minimalist game
    And Scientist is located at Essen
    And Scientist hand is Paris,Algiers
    Then Scientist should not be able to direct flight to New_York

 #Charter Flight
 #Play the card corresponding to your pawn’s current location, and move to any city on the board. Discard the card to the Player Discard pile.

  Scenario: Charter Flight
    Given a minimalist game
    And Scientist is located at Essen
    And Scientist hand is Essen,Algiers
    When Scientist charter flies to New_York
    Then Scientist should be located at New_York
    And Scientist hand should be Algiers
    And the player discard pile should contains Essen

  Scenario: Direct Flight
    Given a minimalist game
    And Scientist is located at Essen
    And Scientist hand is Paris,Algiers
    Then Scientist should not be able to charter flight to New_York

 #A player may also elect to pass (and do nothing) for an action.
 #The Dispatcher may move other player’s pawns on his turn (using any of the available Basic actions) as if they were his own pawn. He may also spend an action to move a pawn to any city that contains another pawn. He may only move other players’ pawns if they permit him to do so. Note: For the Charter Flight action, the Dispatcher must play the card corresponding to the current location of the pawn he wishes to move.