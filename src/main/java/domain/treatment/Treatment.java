package domain.treatment;

import domain.board.City;
import infra.World;

import static infra.World.cubeBank;

public class Treatment implements TreatmentListener {

    @Override
    public void onTreatment(TreatmentEvent treatmentEvent) {
        City city = World.network.get(treatmentEvent.cityName);
        city.treat(treatmentEvent.disease);
        cubeBank.putBackCube(treatmentEvent.disease);
    }

}
