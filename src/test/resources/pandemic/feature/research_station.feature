Feature: Research Station

  Build a Research Station
  Discard the City card that matches the city you are in to place a research
  station there. Take the research station from the pile next to the board. If all
  6 research stations have been built, take a research station from anywhere
  on the board.

  Scenario: build a research station
    Given a standard game
    And Medic is located at Algiers
    And Medic hand is Algiers,Paris
    And it is the turn of Medic
    When Medic build a research station
    Then a research station should have been built on Algiers
    And Medic hand should be Paris

  Scenario: A player can build a research station
    Given a standard game
    And Medic is located at Algiers
    And Medic hand is Algiers,Paris
    And it is the turn of Medic
    Then Medic should be able to build a research station

  Scenario: A player can build a research station
    Given a standard game
    And Medic is located at Algiers
    And Medic hand is Lima,Paris
    And it is the turn of Medic
    Then Medic should not be able to build a research station

  Scenario: A player cannot build 2 research stations on the same city
    Given a standard game
    And Medic is located at Algiers
    And Medic hand is Algiers,Paris
    And a research station has been built on Algiers
    Then Medic should not be able to build a research station

    #TODO If all 6 research stations have been built, take a research station from anywhere on the board.

  Scenario: There are only 6 stations
    Given a standard game
    And a research station has been built on Paris,London,Essen,Milan,Madrid,Algiers
    And Medic is located at New_york
    And Medic hand is New_york
    And it is the turn of Medic
    Then Medic should not be able to build a research station

  Scenario: There are only 6 stations, move a research station instead of building it
    Given a standard game
    And a research station has been built on Paris,London,Essen,Milan,Madrid,Algiers
    And Medic is located at New_york
    And Medic hand contains New_york
    And it is the turn of Medic
    Then Medic should be able to move a research station

  Scenario: move a research station
    Given a standard game
    And a research station has been built on Paris,London,Essen,Milan,Madrid,Algiers
    And Medic is located at New_york
    And Medic hand is New_york,Paris
    And it is the turn of Medic
    When Medic move a research station from Madrid
    Then a research station should be on New_York
    And Madrid should not have research station anymore
    And Medic hand should be Paris
