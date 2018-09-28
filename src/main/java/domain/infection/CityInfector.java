package domain.infection;

import domain.game.TurnId;
import domain.infection.cards.InfectionCardDrawnEvent;
import domain.network.City;
import domain.network.CityName;
import infra.InfectionCardDrawnListener;
import infra.World;

public class CityInfector implements InfectionListener, InfectionCardDrawnListener {

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        City city = World.game.network.get(infectionEvent.cityName);
        InfectionLevel infectionLevel = city.infectionLevelFor(infectionEvent.disease);

        if (!infectionLevel.outbreakLevelReached() && !World.game.cureMarkerArea.hasBeenEradicated(infectionEvent.disease)) {
            World.game.cubeBank.takeCube(infectionEvent.disease);
            city.infect(infectionEvent.disease);
        }
    }

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
