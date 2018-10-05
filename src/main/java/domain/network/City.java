package domain.network;

import domain.infection.Disease;
import domain.infection.InfectionLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class City {

    private final CityName cityName;
    private Map<Disease, InfectionLevel> infections = new HashMap<>();

    City(CityName cityName) {
        this.cityName = cityName;
        Stream.of(Disease.values())
                .forEach(this::initInfectionLevel);
    }

    private InfectionLevel initInfectionLevel(Disease disease) {
        return infections.put(disease, InfectionLevel.ZERO);
    }

    public void putCube(Disease disease) {
        infections.put(disease, infectionLevelFor(disease).increase());
    }

    public void treat(Disease disease) {
        infections.put(disease, infectionLevelFor(disease).decrease());
    }
    public InfectionLevel infectionLevelFor(Disease disease) {
        return infections.get(disease);
    }

    public boolean outbreakInfectionLevelReached(Disease disease) {
        return infectionLevelFor(disease).outbreakLevelReached();
    }

    public boolean isHealthyFor(Disease disease) {
        return infectionLevelFor(disease).isZero();
    }
}
