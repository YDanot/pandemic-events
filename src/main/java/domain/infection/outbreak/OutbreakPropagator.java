package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;
import domain.events.InfectionEvent;
import domain.events.OutbreakEvent;
import domain.infection.Disease;
import infra.World;

public class OutbreakPropagator implements OutbreakListener{

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {
        World.network.citiesLinkedTo(outbreakEvent.cityName).forEach(city -> infect(city, outbreakEvent.disease, outbreakEvent.turnId));
    }

    private void infect(CityName c, Disease disease, TurnId turnId) {
        World.eventBus.publish(new InfectionEvent(disease, World.network.get(c), turnId));
    }
}
