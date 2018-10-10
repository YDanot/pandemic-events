Feature: epidemic

  Whenever a player draws an Epidemic card, discard the card into the Player Discard Pile and do the following:

  Increase the Infection Rate: Move the Infection Rate Indicator up by one on the Infection Rate Track on the board

  Infect: Take the bottom card from the Infection Draw Pile and add 3 cubes to the city pictured on the card, then place the card into the Infection Discard Pile. Note: No city can contain more than 3 cubes of any one color. If the Epidemic would cause the city to exceed that limit, any excess cubes are returned to the stock and an outbreak is triggered. If there are not enough cubes to add to the board during an Epidemic, the game immediately ends in defeat for all players.

  Increase the intensity of infection: Take the Infection Discard Pile, thoroughly shuffle it, then place it on top of the remaining Infection Draw Pile. (Don’t shuffle these cards into the Infection Draw Pile.)

  Scenario: Increase infection rate on Epidemic
    Given a minimalist game
    And the infection rate indicator is placed on 3rd rate
    When an epidemic occurred
    Then the infection rate should be 3

  Scenario: Infect bottom card Epidemic
    Given a minimalist game
    And the bottom card from the Infection Draw Pile is Paris
    When an epidemic occurred
    Then Blue infection level of Paris should be 3

  Scenario: Infect bottom card Epidemic when it's already infected
    Given a minimalist game
    And there already were 2 outbreaks
    And Paris has already been infected by Blue 2 times
    And the bottom card from the Infection Draw Pile is Paris
    When an epidemic occurred
    Then Blue infection level of Paris should be 3
    And outbreak counter value should be 3

  Scenario: Increase the intensity of infection
    Given a minimalist game
    And the infection discard pile is Paris,Algiers,New_York
    And the bottom card from the Infection Draw Pile is London
    When an epidemic occurred
    Then top cards of the Infection Draw Pile should be Paris,Algiers,New_York,London

  Scenario: Epidemic launched after drawing epidemic card
    Given a minimalist game
    And Player draw pile starts with Epidemic
    When a player draws a card
    Then epidemic should occurs