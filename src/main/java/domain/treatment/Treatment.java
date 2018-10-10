package domain.treatment;

import domain.infection.Disease;
import domain.network.City;
import infra.World;

public class Treatment implements TreatmentListener {

    @Override
    public void onTreatment(TreatmentEvent treatmentEvent) {
        City city = World.board.network.get(treatmentEvent.cityName);
        Disease disease = treatmentEvent.disease;
        if (World.board.cureMarkerArea.hasBeenCured(disease)) {
            while (!city.isHealthyFor(disease)) {
                treat(city, disease);
            }
        } else {
            treat(city, disease);
        }
    }

    private void treat(City city, Disease disease) {
        city.treat(disease);
        World.board.cubeBank.putBackCube(disease);
    }

}
