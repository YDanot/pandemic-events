Feature: Standard Definition

  Scenario: Occident network definition

#   (LONDON)___(ESSEN)
#     | \       / |
#     |  \     /  |
#     |   \   /   |
#     |  (PARIS)  |
#     |   / | \   |
#     |  /  |  \  |
#     | /   |   \ |
#  (MADRID) | (MILAN)
#        \  |
#        (ALGER)

    Given the occident initial sub-network
    Then the cities should have the following infection levels:
      | cityName | level |
      | Paris    | 0     |
      | London   | 0     |
      | Essen    | 0     |
      | Alger    | 0     |
      | Madrid   | 0     |
      | Milan    | 0     |
    And Paris should be linked to London, Essen, Milan, Alger, Madrid.
    And Essen should be linked to London, Paris, Milan.
    And Madrid should be linked to London, Paris, Alger.