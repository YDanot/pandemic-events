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
#                         (ALGER)

    Given the occident initial sub-network
    Then the cities should have the following infection levels:
      | cityName | blue level | black level |
      | Paris    | 0          | 0           |
      | London   | 0          | 0           |
      | Essen    | 0          | 0           |
      | Alger    | 0          | 0           |
      | Madrid   | 0          | 0           |
      | Milan    | 0          | 0           |
      | New_york | 0          | 0           |
    And Paris should be linked to London, Essen, Milan, Alger, Madrid.
    And Essen should be linked to London, Paris, Milan.
    And Madrid should be linked to London, Paris, Alger.
    And There should be 24 blue cubes available
    And There should be 24 black cubes available
    Then game should be available