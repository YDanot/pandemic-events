package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;
import domain.events.InfectionEvent;
import domain.events.OutbreakEvent;
import domain.infection.City;
import domain.infection.Disease;
import domain.infection.InfectionListener;
import infra.World;

public class OutbreakDetector implements InfectionListener {

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        if (shouldOutbreak(infectionEvent.disease, infectionEvent.city, infectionEvent.turnId)){
            outbreak(infectionEvent.disease, infectionEvent.city.name(), infectionEvent.turnId);
        }
    }

    private boolean shouldOutbreak(Disease disease, City city, TurnId turnId) {
        return city.outbreakInfectionLevelReached(disease) && !alreadyOutbrokenInTurn(disease, city.name(), turnId);
    }

    private boolean alreadyOutbrokenInTurn(Disease disease, CityName cityName, TurnId turnId) {
        return World.outbrokenCityFinder.find(turnId, disease).contains(cityName);
    }

    private void outbreak(Disease disease, CityName cityName, TurnId turnId) {
        World.outbrokenCityMarker.mark(turnId, cityName, disease);
        World.eventBus.publish(new OutbreakEvent(cityName, disease, turnId));
    }
}
