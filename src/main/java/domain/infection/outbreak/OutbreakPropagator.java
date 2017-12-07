package domain.infection.outbreak;

import domain.game.TurnId;
import domain.board.CityName;
import domain.events.InfectionEvent;
import domain.events.OutbreakEvent;
import infra.World;

public class OutbreakPropagator implements OutbreakListener{

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {
        World.network.citiesLinkedTo(outbreakEvent.cityName).forEach(c -> infect(c, outbreakEvent.turnId));
    }

    private void infect(CityName c, TurnId turnId) {
        World.eventBus.publish(new InfectionEvent(World.network.get(c), turnId));
    }
}
