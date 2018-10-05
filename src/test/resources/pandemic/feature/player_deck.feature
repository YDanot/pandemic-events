Feature: Player cards

  Scenario: a player card by city should be in the player card draw pile
    Given a minimalist game
    Then Player draw pile should contains cities Paris,London,New_York,Essen,Milan,Madrid,Algiers in any order
