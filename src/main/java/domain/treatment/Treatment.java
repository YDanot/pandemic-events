package domain.treatment;

import domain.board.City;
import domain.infection.Disease;
import infra.World;

import static infra.World.cubeBank;

public class Treatment implements TreatmentListener {

    @Override
    public void onTreatment(TreatmentEvent treatmentEvent) {
        City city = World.network.get(treatmentEvent.cityName);
        Disease disease = treatmentEvent.disease;
        if (World.cureMarkerArea.isCured(disease)) {
            while (!city.isHealthyFor(disease)) {
                treat(city, disease);
            }
        } else {
            treat(city, disease);
        }
    }

    private void treat(City city, Disease disease) {
        city.treat(disease);
        cubeBank.putBackCube(disease);
    }

}
