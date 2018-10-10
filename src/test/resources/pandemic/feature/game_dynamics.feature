Feature: Dynamics

  Play proceeds clockwise around the table with each player taking turns in order until the game ends.
  Each turn, the current player must:

  Take 4 actions
  Draw 2 cards to add to his hand
  Take on the role of the Infector

  After taking the role of the Infector, the player’s turn is over and the player on his left then begins his turn.

  Scenario: first action in a turn
    Given a standard game
    Then Scientist should be able to take an action

  Scenario: fourth action in a turn
    Given a standard game
    And Scientist has driven to Washington
    And Scientist has driven to Montreal
    And Scientist has driven to New_York
    Then Scientist should be able to take an action

  Scenario: fifth action in a turn
    Given a standard game
    And Scientist has taken 4 actions
    Then Scientist should not be able to take an action

  Scenario: draw the first player card
    Given a standard game
    And Scientist has taken 4 actions
    When Scientist draws a card
    Then drawing phase should not be over

  Scenario: draw the second player card
    Given a standard game
    And Scientist has taken 4 actions
    And Scientist has drawn a card
    When Scientist draws a card
    Then Scientist should not be able to draw another card


  Scenario: limit the hand to 7 cards
    Given a standard game
    And Scientist has taken 4 actions
    And Scientist hand is Jakarta,Lagos,Washington,Moscow,Paris,London
    And Scientist has drawn 2 cards
    Then drawing phase should not be over

  Scenario: turn should be over after infector phase
    Given a standard game
    And Scientist has taken 4 actions
    And Scientist has drawn 2 cards
    When infector plays
    Then Turn should be over

  Scenario: infector phase
    Given a standard game
    And Infection draw pile starts with Jakarta,Lagos
    When infector plays
    Then infection should occurs on Jakarta
    And infection should occurs on Lagos
