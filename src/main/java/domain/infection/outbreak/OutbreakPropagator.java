package domain.infection.outbreak;

import domain.board.City;
import domain.game.TurnId;
import domain.board.CityName;
import domain.infection.InfectionEvent;
import domain.infection.Disease;
import infra.World;

public class OutbreakPropagator implements OutbreakListener{

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {
        World.network.citiesLinkedTo(outbreakEvent.cityName).forEach(city -> infect(city, outbreakEvent.disease, outbreakEvent.turnId));
    }

    private void infect(CityName cityName, Disease disease, TurnId turnId) {
        City city = World.network.get(cityName);
        World.eventBus.publish(new InfectionEvent(disease, cityName, turnId, city.infectionLevelFor(disease)));
    }
}
