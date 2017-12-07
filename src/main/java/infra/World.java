package infra;

import domain.board.Network;
import domain.infection.CityInfector;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.outbreak.OutbreakDetector;
import domain.infection.outbreak.OutbreakPropagator;
import domain.infection.outbreak.OutbrokenCityFinder;

public class World {

    public static EventBus eventBus;
    public static Network network;
    public static OutbreakCounter outbreakCounter;
    public static OutbrokenCityFinder outbrokenCityFinder = new EventSourcingOutbrokenCityDao();

    public static void create() {
        eventBus = new EventBus();
        network = new Network();
        outbreakCounter = new OutbreakCounter();
        eventBus.listenInfection(new CityInfector());
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(outbreakCounter);
    }

}
