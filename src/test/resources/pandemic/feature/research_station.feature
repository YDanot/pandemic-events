Feature: Research Station

  Scenario: A player can build a research station
    Given a minimalist game
    And Medic is located at Algiers
    Then Medic should be able to build a research station

  Scenario: A player cannot build 2 research stations on the same city
    Given a minimalist game
    And Medic is located at Algiers
    And a research station has been built on Algiers
    Then Medic should not be able to build a research station

  Scenario: There are only 6 stations
    Given a minimalist game
    And a research station has been built on Paris,London,Essen,Milan,Madrid,Algiers
    And Medic is located at New_york
    Then Medic should not be able to build a research station
