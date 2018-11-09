Feature: Researcher Role handling

  Researcher : When doing the Share Knowledge action, the Researcher
  may give any City card from her hand to another player
  in the same city as her, without this card having to match
  her city. The transfer must be from her hand to the other
  player’s hand, but it can occur on either player’s turn.

  Scenario: Researcher may give any City card from her hand to another player in the same city as her
    Given a standard game
    But Scientist, Researcher are playing
    And Scientist is located at Paris
    And Researcher is located at Paris
    And it is the turn of Researcher
    Then Researcher should be able to share knowledge


  Scenario: Researcher's turn
    Given a standard game
    But Scientist, Researcher are playing
    And Scientist is located at Paris
    And Researcher is located at Paris
    And Researcher hand is Baghdad,New_York
    And Scientist hand is Essen,Algiers
    And it is the turn of Researcher
    When Researcher shares Baghdad with Scientist
    Then Scientist hand should be Essen,Algiers,Baghdad
    And Researcher hand should be New_York


  Scenario: Researcher may give any City card from her hand to another player in the same city as her
    Given a standard game
    But Scientist, Researcher are playing
    And Scientist is located at Paris
    And Researcher is located at Paris
    And it is the turn of Scientist
    Then Scientist should be able to ask Researcher to share knowledge

  Scenario: other player's turn
    Given a standard game
    But Scientist, Researcher are playing
    And Scientist is located at Paris
    And Researcher is located at Paris
    And Researcher hand is Baghdad,New_York
    And Scientist hand is Essen,Algiers
    And it is the turn of Scientist
    When Scientist ask Researcher to share Baghdad
    Then Scientist hand should be Essen,Algiers,Baghdad
    And Researcher hand should be New_York