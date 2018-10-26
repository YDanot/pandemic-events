Feature: Discover a cure

  Take a Cure marker and place it on the Discovered Cures area of the board to indicate which disease has been cured.
  Once your team has discovered all four cures, you win!

  #Place the spent cards into the Player Discard Pile.
  #If your pawn is in a city with a Research Station, discard 5 cards of the same color to cure the corresponding disease.
  #The Scientist only needs 4 cards of a color to discover the cure of the corresponding disease when performing the Discover Cure action.


  Scenario: discover a cure for blue disease
    Given a standard game
    And MEDIC hand is Essen,Paris,New_York,London,Washington,Algiers
    And MEDIC is located at Atlanta
    When MEDIC cures Blue disease by discarding Essen,Paris,New_York,London,Washington
    Then Blue should be mark as cured

  Scenario: win the game on discovering all cures
    Given a standard game
    And MEDIC hand is Essen,Paris,New_York,London,Washington,Algiers
    And MEDIC is located at Atlanta
    And Yellow has been cured
    And Black has been cured
    And Red has been cured
    When MEDIC cures Blue disease by discarding Essen,Paris,New_York,London,Washington
    Then game should be won

  Scenario: discard 5 cards of the same color to cure the corresponding disease.
    Given a standard game
    And MEDIC hand is Essen,Paris,New_York,London,Washington,Algiers
    And MEDIC is located at Atlanta
    When MEDIC cures Blue disease by discarding Essen,Paris,New_York,London,Washington
    Then MEDIC hand should be Algiers
    And the player discard pile should contains Essen,Paris,New_York,London,Washington

  Scenario: cure a disease only if you have 5 cards on the color of the disease.
    Given a standard game
    And MEDIC hand is Essen,Paris,New_York,London,Washington,Algiers
    And MEDIC is located at Atlanta
    Then MEDIC should not be able to cure Red disease

  Scenario: cure a disease in a research station only.
    Given a standard game
    And MEDIC hand is Essen,Paris,New_York,London,Washington,Algiers
    And MEDIC is located at Jakarta
    Then MEDIC should not be able to cure Blue disease


  Scenario: possibility to discover a cure
    Given a standard game
    And MEDIC hand is Essen,Paris,New_York,London,Washington,Algiers
    And MEDIC is located at Atlanta
    And it is the turn of MEDIC
    Then MEDIC should be able to cure Blue Disease
