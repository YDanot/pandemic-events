Feature: Eradicating a Disease


  If a cure for a given disease has been discovered
  and all of the disease cubes of that color have been removed from the board,
  flip the Cure marker for the disease to the “Sunset” side.

  From now on, cards of this color have no effect when drawn on the Infector’s turn.
  Take all of the cubes of the eradicated color and place them back in the box—they will not be used again for the rest of the game.

  #The Medic may remove all the cubes of a single color (instead of 1) when performing the Treat Disease action.
  #Also, if the Medic at any time finds herself in a city that contains cubes of a disease that has been cured,
  #she may immediately remove all of those cubes.
  #is unique ability is in effect during all players’ turns and does not cost any actions to perform.

  Scenario: No city can be infected when disease is eradicated
    Given the occident initial sub-network
    And Blue has been eradicated
    When Paris is infected by Blue
    Then Blue infection level of Paris should be 0