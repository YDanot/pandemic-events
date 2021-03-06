package domain.infection.outbreak;

import domain.game.TurnId;
import domain.infection.Disease;
import domain.infection.InfectionEvent;
import domain.infection.InfectionLevel;
import domain.infection.InfectionListener;
import domain.network.CityName;
import infra.EventSourcingOutbrokenCityDao;
import infra.World;

public class OutbreakDetector implements InfectionListener {

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        if (shouldOutbreak(infectionEvent.disease, infectionEvent.cityName, infectionEvent.turnId, infectionEvent.infectionLevel)){
            outbreak(infectionEvent.disease, infectionEvent.cityName, infectionEvent.turnId);
        }
    }

    private boolean shouldOutbreak(Disease disease, CityName cityName, TurnId turnId, InfectionLevel infectionLevel) {
        return infectionLevel.outbreakLevelReached() && !alreadyOutbrokenInTurn(disease, cityName, turnId);
    }

    private boolean alreadyOutbrokenInTurn(Disease disease, CityName cityName, TurnId turnId) {
        return new EventSourcingOutbrokenCityDao().find(turnId, disease).contains(cityName);
    }

    private void outbreak(Disease disease, CityName cityName, TurnId turnId) {
        World.eventBus.publish(new OutbreakEvent(cityName, disease, turnId));
    }
}
