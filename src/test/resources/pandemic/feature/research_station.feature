Feature: Research Station

  Scenario: build a research station
    Given a standard game
    And Medic is located at Algiers
    And it is the turn of Medic
    When Medic build a research station
    Then a research station should have been built on Algiers

  Scenario: A player can build a research station
    Given a standard game
    And Medic is located at Algiers
    And it is the turn of Medic
    Then Medic should be able to build a research station

  Scenario: A player cannot build 2 research stations on the same city
    Given a standard game
    And Medic is located at Algiers
    And a research station has been built on Algiers
    Then Medic should not be able to build a research station

  Scenario: There are only 6 stations
    Given a standard game
    And a research station has been built on Paris,London,Essen,Milan,Madrid,Algiers
    And Medic is located at New_york
    Then Medic should not be able to build a research station
