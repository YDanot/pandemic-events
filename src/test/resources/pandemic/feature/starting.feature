Feature: start a game

  1 - Place the Board in the center of the table within easy reach of all the players.

  2 - Shuffle the Role cards and deal 1 to each player. Each player takes their corresponding pawn and puts it in Atlanta. Put excess Role cards and pawns (if any) back into the box.

  3 - Place 1 Research Station in Atlanta, and place the others near the side of the board.

  4 - Put the Outbreaks Marker on the “0” space of the Outbreaks Indicator, the Infection Rate Marker on the first space of the Infection Rate Track (marked “2”), and the 4 Cure Markers near the Cures Discovered Area of the board.


  5 - Pull the 6 Epidemic cards out of the Player card deck and set them aside for now.

  a) Shuffle the remaining Player cards (with the blue backs) and deal them to the players face down:

  4 player game: 2 cards each
  3 player game: 3 cards each
  2 player game: 4 cards each

  b) Divide the remaining Player cards into a number of piles according to how difficult you’d like to make the game. Make the piles as equal in size as is possible.

  For the Introductory Game, divide the cards into 4 piles. (Use this option if this is your first game.)
  For the Normal Game, divide the cards into 5 piles.
  For the Heroic Game, divide the cards into 6 piles. (Once you’ve mastered the Normal Game.)

  c) Shuffle an Epidemic card into each pile. Stack the piles on top of each other to form the Player Draw Pile. (If the piles aren’t exactly the same size, stack them so that the larger piles are above the smaller piles.) Put any excess Epidemic cards back into the box.

  6 - Shuffle the Infection cards (with the green backs) and place them face down on the board to form the Infection Draw Pile.

  7 - Put the initial Disease cubes on the board:
  a) Draw 3 cards from the Infection Draw Pile and place them face up into the Infection Discard Pile. For each card drawn, add 3 cubes (of the color of the card) to each pictured city.
  b) Draw 3 more cards and do the same thing as above, but add 2 cubes to each pictured city.
  c) Draw 3 final cards and do the same as above, but add 1 cube to each city

  Scenario: Put the initial Disease cubes on the board
    Given a standard game
    And Infection draw pile starts with Paris,Bogota,Osaka,Moscow,Milan,Manila,Algiers,Istanbul,Jakarta
    When we put initial disease cubes on the board
    Then Blue infection level of Paris should be 3
    And Yellow infection level of Bogota should be 3
    And Red infection level of Osaka should be 3
    And Black infection level of Moscow should be 2
    And Blue infection level of Milan should be 2
    And Red infection level of Manila should be 2
    And Black infection level of Algiers should be 1
    And Black infection level of Istanbul should be 1
    And Red infection level of Jakarta should be 1
    And the infection discard pile is Paris,Bogota,Osaka,Moscow,Milan,Manila,Algiers,Istanbul,Jakarta

  Scenario: dealing player cards
    Given a standard game
    And Player draw pile starts with Paris,London,New_York,Essen,Milan,Madrid,Algiers,Jakarta
    And players are Medic, Scientist
    When we deal cards
    Then a player should have Paris,New_York in his hand
    And a player should have London,Essen in his hand
    And Player draw pile should not contains cities Paris,London,New_York,Essen,Milan,Madrid,Algiers,Jakarta

  Scenario: epidemic cards should be added into Player pile by dividing it by 4 for introduction game
    Given a standard game
    And Level is Introduction
    And cards has been dealt
    When we add Epidemic cards to draw Pile
    Then Player draw pile should contains 4 Epidemic cards
    And we should have one epidemic card on each 1/4 of player card pile

