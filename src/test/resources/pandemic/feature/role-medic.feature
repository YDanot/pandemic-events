Feature: Medic Role handling

  The Medic removes all cubes, not 1, of the same color
  when doing the Treat Disease action.

  If a disease has been cured, he automatically removes
  all cubes of that color from a city, simply by entering it
  or being there. This does not take an action.

  Scenario: Medic removes all cubes of the same color when doing the Treat Disease action
    Given a standard game
    And it is the turn of Medic
    And Medic is located at Paris
    And Paris has already been infected by Blue 3 times
    When Medic treats Blue
    Then Blue infection level of Paris should be 0
