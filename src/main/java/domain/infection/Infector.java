package domain.infection;

import domain.game.TurnId;
import domain.infection.cards.InfectionCardDrawnEvent;
import domain.infection.cards.InfectionCardDrawnListener;
import domain.network.CityName;
import infra.World;

public class Infector implements InfectionCardDrawnListener {

    @Override
    public void onInfectionCardDrawn(InfectionCardDrawnEvent infectionCardDrawnEvent) {
        CityName cityName = getCityName(infectionCardDrawnEvent.card().name());
        Disease disease = infectionCardDrawnEvent.card().disease();
        InfectionEvent infectionEvent = new InfectionEvent(disease, cityName, new TurnId(), World.game.network.get(cityName).infectionLevelFor(disease));
        World.eventBus.publish(infectionEvent);
    }


    private CityName getCityName(String name) {
        return CityName.valueOf(name);
    }
}
