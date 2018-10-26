package domain.treatment;

import domain.infection.Disease;
import domain.network.City;
import domain.role.Role;
import infra.World;

public class Treatment implements TreatmentListener {

    @Override
    public void onTreatment(TreatmentEvent treatmentEvent) {
        City city = World.board.network.get(treatmentEvent.cityName);
        Disease disease = treatmentEvent.disease;
        if (World.board.cureMarkerArea.hasBeenCured(disease) || World.game.players.currentTurn().player().role().equals(Role.MEDIC)) {
            fullTreat(city, disease);
        } else {
            treat(city, disease);
        }
    }

    public void fullTreat(City city, Disease disease) {
        while (!city.isHealthyFor(disease)) {
            treat(city, disease);
        }
    }

    private void treat(City city, Disease disease) {
        city.treat(disease);
        World.board.cubeBank.putBackCube(disease);
    }

}
