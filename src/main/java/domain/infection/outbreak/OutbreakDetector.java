package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;
import domain.events.InfectionEvent;
import domain.events.OutbreakEvent;
import domain.infection.City;
import domain.infection.InfectionListener;
import infra.World;

public class OutbreakDetector implements InfectionListener {

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        if (shouldOutbreak(infectionEvent.city, infectionEvent.turnId)){
            outbreak(infectionEvent.city.name(), infectionEvent.turnId);
        }
    }

    private boolean shouldOutbreak(City city, TurnId turnId) {
        return city.outbreakInfectionLevelReached() && !alreadyOutbrokenInTurn(city.name(), turnId);
    }

    private boolean alreadyOutbrokenInTurn(CityName cityName, TurnId turnId) {
        return World.outbrokenCityFinder.find(turnId).contains(cityName);
    }

    private void outbreak(CityName cityName, TurnId turnId) {
        World.outbrokenCityMarker.mark(turnId, cityName);
        World.eventBus.publish(new OutbreakEvent(cityName, turnId));
    }
}
