Feature: Research Station

  Scenario: A player can build a research station
    Given the occident initial sub-network
    And Medic is located at Alger
    Then Medic should be able to build a research station

  Scenario: A player cannot build 2 research stations on the same city
    Given the occident initial sub-network
    And Medic is located at Alger
    And a research station has been built on Alger
    Then Medic should not be able to build a research station

  Scenario: There are only 6 stations
    Given the occident initial sub-network
    And a research station has been built on Paris,London,Essen,Milan,Madrid,Alger
    And Medic is located at New_york
    Then Medic should not be able to build a research station
