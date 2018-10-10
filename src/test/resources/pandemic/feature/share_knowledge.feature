Feature: sharing knowledge

  Transfer a card from one player to another.
  Every card transferred costs 1 action.
  Both your pawn and your fellow player’s pawn must be in the same city, and you may only transfer the card of the city that you are in together.
  (For example, if you are together in Moscow, only the Moscow card may be transferred from one player to the other.)

  Scenario: transfer a card
    Given a minimalist game
    And Medic is located at Paris
    And Scientist is located at Paris
    And Medic hand is Paris,New_York
    And Scientist hand is Essen,Algiers
    When Medic shares Paris with Scientist
    Then Scientist hand should be Essen,Algiers,Paris
    And Medic hand should be New_York

  Scenario: impossible to transfer a card without card in hand
    Given a minimalist game
    And Medic is located at Paris
    And Scientist is located at Paris
    And Medic hand is London,New_York
    And Scientist hand is Essen,Algiers
    Then Medic should not be able to share Paris with Scientist

  Scenario: impossible to transfer a card without being in the city
    Given a minimalist game
    And Medic is located at Paris
    And Scientist is located at Milan
    And Medic hand is Paris,New_York
    And Scientist hand is Essen,Algiers
    Then Medic should not be able to share Paris with Scientist