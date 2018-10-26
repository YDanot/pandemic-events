Feature: Scientist Role handling

  The Scientist needs only 4 (not 5) City cards of
  the same disease color to Discover a Cure for that
  disease.

  Scenario: Scientist needs only 4 City cards of the same disease color to Discover a Cure
    Given a standard game
    And Scientist hand is Essen,Paris,New_York,London, Algiers
    And Scientist is located at Atlanta
    When Scientist cures Blue disease by discarding Essen,Paris,New_York,London
    Then Blue should have been cured

