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
    And Player draw pile should contains cities Paris,Essen,London,New_York,Milan,Madrid,Algiers in any order
    And Player draw pile should not contains Epidemic cards
    And There should be 24 blue cubes available
    And There should be 24 black cubes available
    Then game should be available

  Scenario: standard network definition
    Given the standard network
    Then Algiers should be linked to Madrid, Paris, Istanbul, Cairo.
    And Atlanta should be linked to Miami, Washington, Chicago.
    And Bogota should be linked to Lima, Mexico_City, Miami, Buenos_Aries, Sao_Paulo.
    And Buenos_Aries should be linked to Sao_Paulo, Bogota.
    And Cairo should be linked to ALGIERS, BAGHDAD, ISTANBUL, KHARTOUM, RIYADH.
    And Lima should be linked to Bogota, Mexico_City, Santiago.
    And London should be linked to New_York, Paris, Madrid, Essen.
    And Los_Angeles should be linked to San_Francisco, Chicago, Sydney, Mexico_City.
    And Madrid should be linked to London, Paris, Sao_Paulo, New_York, Algiers.
    And Mexico_City should be linked to Los_Angeles, Chicago, Miami, Bogota, Lima.
    And Miami should be linked to Atlanta, Washington, Bogota, Mexico_City.
    And Montreal should be linked to Chicago, New_York, Washington.
    And New_York should be linked to Montreal, Washington, Madrid,London.
    And San_Francisco should be linked to Los_Angeles, Chicago, Tokyo, Osaka.
    And Sao_Paulo should be linked to Buenos_Aries, Bogota, Lagos, Madrid.
    And Paris should be linked to London, Essen, Milan, Algiers, Madrid.
    And Baghdad should be linked to CAIRO, ISTANBUL, KARACHI, TEHRAN.
    And Bangkok should be linked to CHENNAI, HO_CHI_MINH_CITY, HONG_KONG, KOLKATA.
    And Beijing should be linked to SEOUL, SHANGHAI.
    And Chennai should be linked to BANGKOK, DELHI, JAKARTA, KOLKATA, MUMBAI.
    And Chicago should be linked to ATLANTA, LOS_ANGELES, MEXICO_CITY, MONTREAL, SAN_FRANCISCO.
    And Delhi should be linked to CHENNAI, KARACHI, KOLKATA, MUMBAI, TEHRAN.
    And Essen should be linked to LONDON, MILAN, PARIS, ST_PETERSBURG.
    And Ho_chi_minh_city should be linked to BANGKOK, HONG_KONG, JAKARTA, MANILA.
    And Hong_kong should be linked to BANGKOK, HO_CHI_MINH_CITY, KOLKATA, MANILA, SHANGHAI, SHANGHAI, TAIPEI.
    And Istanbul should be linked to ALGIERS, BAGHDAD, CAIRO, MILAN, MOSCOW, ST_PETERSBURG.
    And Jakarta should be linked to CHENNAI, HO_CHI_MINH_CITY, SYDNEY.
    And Johannesburg should be linked to KHARTOUM, KINSHASA.
    And Karachi should be linked to BAGHDAD, DELHI, MUMBAI, RIYADH, TEHRAN.
    And Khartoum should be linked to CAIRO, JOHANNESBURG, KINSHASA, LAGOS.
    And Kinshasa should be linked to JOHANNESBURG, KHARTOUM, LAGOS.
    And Kolkata should be linked to BANGKOK, CHENNAI, DELHI, HONG_KONG.
    And Lagos should be linked to KHARTOUM, KINSHASA, SAO_PAULO.
    And Manila should be linked to HO_CHI_MINH_CITY, HONG_KONG, SYDNEY, TAIPEI.
    And Milan should be linked to ESSEN, ISTANBUL, PARIS.
    And Moscow should be linked to ISTANBUL, ST_PETERSBURG, TEHRAN.
    And Mumbai should be linked to CHENNAI, DELHI, KARACHI.
    And Osaka should be linked to SAN_FRANCISCO, TAIPEI, TOKYO.
    And Riyadh should be linked to CAIRO, KARACHI.
    And Santiago should be linked to LIMA.
    And Seoul should be linked to BEIJING, SHANGHAI, TOKYO.
    And Shanghai should be linked to BEIJING, HONG_KONG, HONG_KONG, SEOUL, TAIPEI, TOKYO.
    And St_petersburg should be linked to ESSEN, ISTANBUL, MOSCOW.
    And Sydney should be linked to JAKARTA, LOS_ANGELES, MANILA.
    And Taipei should be linked to HONG_KONG, MANILA, OSAKA, SHANGHAI.
    And Tehran should be linked to BAGHDAD, DELHI, KARACHI, MOSCOW.
    And Tokyo should be linked to OSAKA, SAN_FRANCISCO, SEOUL, SHANGHAI.
    And Washington should be linked to ATLANTA, MIAMI, MONTREAL, NEW_YORK.


  Scenario: standard game definition
    Given a standard game
    Then the standard network should be used
    And Players should be SCIENTIST, MEDIC
    And Game level should be Introduction
    And Players should start in Atlanta
    And a research station should have been built on Atlanta
    And it should be the turn of Scientist