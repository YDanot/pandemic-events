package domain.infection;

import domain.cube.TakeCubeEvent;
import domain.network.City;
import infra.World;

public class CityInfector implements InfectionListener {

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        City city = World.game.network.get(infectionEvent.cityName);
        InfectionLevel infectionLevel = city.infectionLevelFor(infectionEvent.disease);

        if (!infectionLevel.outbreakLevelReached() && !World.game.cureMarkerArea.hasBeenEradicated(infectionEvent.disease)) {
            World.eventBus.publish(new TakeCubeEvent(infectionEvent.disease));
            city.infect(infectionEvent.disease);
        }
    }

}
