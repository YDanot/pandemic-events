Feature: Player cards

  Scenario: a player card by city should be in the player card draw pile
    Given a minimalist game
    Then Player draw pile should contains cities Paris,London,New_York,Essen,Milan,Madrid,Algiers in any order

  Scenario: a player card by city should be in the player card draw pile
    Given a minimalist game
    And Player draw pile is Paris,London,New_York,Essen,Milan,Madrid,Algiers
    And players are Medic, Scientist
    When the game starts with 2 epidemic cards
    Then a player should have Paris,New_York in his hand
    And a player should have London,Essen in his hand
    Then Player draw pile should contains cities Milan,Madrid,Algiers in any order

  Scenario: epidemic cards should be added after dealing first player cards
    Given a minimalist game
    And Player draw pile is Paris,London,New_York,Essen,Milan,Madrid,Algiers
    And players are Medic, Scientist
    When the game starts with 2 epidemic cards
    Then Player draw pile should contains 2 Epidemic cards