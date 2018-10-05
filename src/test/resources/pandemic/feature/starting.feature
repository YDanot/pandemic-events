Feature: start a game

  Put the initial Disease cubes on the board:
  a) Draw 3 cards from the Infection Draw Pile and place them face up into the Infection Discard Pile. For each card drawn, add 3 cubes (of the color of the card) to each pictured city.
  b) Draw 3 more cards and do the same thing as above, but add 2 cubes to each pictured city.
  c) Draw 3 final cards and do the same as above, but add 1 cube to each city

  Scenario: Put the initial Disease cubes on the board
    Given a standard game
    And Player draw pile is Paris,London,New_York,Essen,Milan,Madrid,Algiers