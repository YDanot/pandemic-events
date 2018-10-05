Feature: Discover a cure

  Take a Cure marker and place it on the Discovered Cures area of the board to indicate which disease has been cured.
  Once your team has discovered all four cures, you win!

  #Place the spent cards into the Player Discard Pile.
  #If your pawn is in a city with a Research Station, discard 5 cards of the same color to cure the corresponding disease.
  #The Scientist only needs 4 cards of a color to discover the cure of the corresponding disease when performing the Discover Cure action.


  Scenario: discover a cure for blue disease
    Given a minimalist game
    When Blue is cured
    Then Blue should be mark as cured

  Scenario: win the game on discovering all cures
    Given a minimalist game
    And Blue has been cured
    And Black has been cured
    And Red has been cured
    When Yellow is cured
    Then game should be won