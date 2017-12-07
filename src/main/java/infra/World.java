package infra;

import domain.infection.Disease;
import domain.board.Network;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.outbreak.OutbreakDetector;
import domain.infection.outbreak.OutbreakPropagator;
import domain.infection.outbreak.OutbrokenCityFinder;
import domain.infection.outbreak.OutbrokenCityMarker;

public class World {

    public static EventBus eventBus;
    public static Network network;
    public static OutbreakCounter outbreakCounter;
    public static OutbrokenCityFinder outbrokenCityFinder;
    public static OutbrokenCityMarker outbrokenCityMarker;

    static {
        InMemoryOutbrokenCityDao inMemoryOutbrokenCityDao = new InMemoryOutbrokenCityDao();
        outbrokenCityFinder = inMemoryOutbrokenCityDao;
        outbrokenCityMarker = inMemoryOutbrokenCityDao;
    }

    public static void create() {
        eventBus = new EventBus();
        network = new Network();
        outbreakCounter = new OutbreakCounter();
        eventBus.listenInfection(Disease.BLUE);
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(outbreakCounter);
    }

}
