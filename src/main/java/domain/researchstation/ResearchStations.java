package domain.researchstation;


import domain.network.CityName;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ResearchStations {

    private static final int NUMBER_OF_STATIONS = 6;
    public final Set<CityName> locations;

    public ResearchStations(CityName... locations) {
        this.locations = new HashSet<>(NUMBER_OF_STATIONS);
        Arrays.stream(locations).limit(NUMBER_OF_STATIONS).forEach(this.locations::add);
    }

    public void buildOn(CityName location) throws ResearchStationException {
        locations.add(location);
    }

    public boolean builtOn(CityName location) {
        return locations.contains(location);
    }

    public boolean stationAvailable() {
        return locations.size() <= NUMBER_OF_STATIONS;
    }

    public boolean buildableIn(CityName location) {
        return stationAvailable() && !builtOn(location);
    }

    public void move(CityName from, CityName to) {
        locations.remove(from);
        buildOn(to);
    }
}
