Feature: Standard Definition

  Scenario: Occident network definition

#             (LONDON)------------------(ESSEN)
#               | \                     /  |
#               |    \                /    |
#               |       \           /      |
#  (NEW-YORK)   |          (PARIS)         |
#               |       /    |     \       |
#               |    /       |        \    |
#               | /          |           \ |
#            (MADRID)        |          (MILAN)
#                \           |
#                   \        |
#                      \     |
#                         \  |
#                         (ALGIERS)

    Given the occident initial sub-network
    Then the cities should have the following infection levels:
      | cityName | blue level | black level |
      | Paris    | 0          | 0           |
      | London   | 0          | 0           |
      | Essen    | 0          | 0           |
      | Algiers  | 0          | 0           |
      | Madrid   | 0          | 0           |
      | Milan    | 0          | 0           |
      | New_york | 0          | 0           |
    And Paris should be linked to London, Essen, Milan, Algiers, Madrid.
    And Essen should be linked to London, Paris, Milan.
    And Madrid should be linked to London, Paris, Algiers.

  Scenario: Minimalist game definition
    Given a minimalist game
    Then occident initial sub-network should be used
    And Infection draw pile should contains at least Paris,Essen,London,New_York,Milan,Madrid,Algiers
    And Player draw pile should contains at least Paris,Essen,London,New_York,Milan,Madrid,Algiers,Epidemic
    And There should be 24 blue cubes available
    And There should be 24 black cubes available
    Then game should be available
