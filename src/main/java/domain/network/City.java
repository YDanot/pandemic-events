package domain.network;

import domain.infection.Disease;
import domain.infection.InfectionLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class City {

    private Map<Disease, InfectionLevel> infections = new HashMap<>();

    City() {
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

    public boolean isHealthyFor(Disease disease) {
        return infectionLevelFor(disease).isZero();
    }

    public boolean isInfected() {
        return infections.values().stream().anyMatch((i) -> !i.isZero());
    }
}
