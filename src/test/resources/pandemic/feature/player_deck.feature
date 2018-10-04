Feature: Player cards

  Scenario: a player card by city should be in the player card draw pile
    Given the occident initial sub-network
    Then Player draw pile should contains at least Paris,London,New_York,Essen,Milan,Madrid,Algiers,Epidemic

  Scenario: a player card by city should be in the player card draw pile
    Given the occident initial sub-network
    And Player draw pile is Paris,London,New_York,Essen,Milan,Madrid,Algiers,Epidemic
    And players are Medic, Scientist
    When the game starts
    Then a player should have Paris,New_York in his hand
    And a player should have London,Essen in his hand
    Then Player draw pile should contains at least Milan,Madrid,Algiers,Epidemic