package domain.infection;

import domain.cube.TakeCubeEvent;
import domain.network.City;
import infra.World;

public class CityInfector implements InfectionListener {

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        City city = World.board.network.get(infectionEvent.cityName);
        InfectionLevel infectionLevel = city.infectionLevelFor(infectionEvent.disease);

        if (!infectionLevel.outbreakLevelReached() && !World.board.cureMarkerArea.hasBeenEradicated(infectionEvent.disease)) {
            World.eventBus.publish(new TakeCubeEvent(infectionEvent.disease));
            city.putCube(infectionEvent.disease);
        }
    }

}
