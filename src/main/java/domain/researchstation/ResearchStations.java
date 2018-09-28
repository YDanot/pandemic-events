package domain.researchstation;


import domain.network.CityName;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ResearchStations {

    private final Set<CityName> locations;

    public ResearchStations(CityName... locations) {
        this.locations = new HashSet<>(6);
        Arrays.stream(locations).limit(6).forEach(this.locations::add);
    }

    void buildOn(CityName location) throws ResearchStationException {
        if (builtOn(location)) {
            throw new OnlyOneResearchStationException();
        } else if (locations.size() == 6) {
            throw new NoMoreResearchStationAvailableException();
        }
        locations.add(location);
    }

    boolean builtOn(CityName location) {
        return locations.contains(location);
    }
}
