package domain.researchstation;


import domain.network.CityName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResearchStations {

    private final List<CityName> locations;

    public ResearchStations(CityName... locations) {
        this.locations = new ArrayList<>();
        this.locations.addAll(Arrays.asList(locations));
    }

    void buildOn(CityName location) throws OnlyOneResearchStationException {
        if (builtOn(location)) {
            throw new OnlyOneResearchStationException();
        }
        locations.add(location);
    }

    boolean builtOn(CityName location) {
        return locations.contains(location);
    }
}
