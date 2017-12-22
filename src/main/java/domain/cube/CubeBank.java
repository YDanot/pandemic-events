package domain.cube;


import domain.infection.Disease;
import domain.infection.InfectionEvent;
import domain.infection.InfectionListener;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CubeBank implements InfectionListener {

    private static final int CUBE_INITIAL_NUMBER = 40;

    private Map<Disease, Integer> cubesByDisease = new HashMap<>();

    public CubeBank() {
        Stream.of(Disease.values())
                .forEach(this::initCubeNumber);
    }


    private void initCubeNumber(Disease disease) {
        cubesByDisease.put(disease, CUBE_INITIAL_NUMBER);
    }

    public Integer getRemainingCubes(Disease disease) {
        return cubesByDisease.get(disease);
    }

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        int cubeNumber = getRemainingCubes(infectionEvent.disease) - 1;
        cubesByDisease.put(infectionEvent.disease, cubeNumber);
    }
}
