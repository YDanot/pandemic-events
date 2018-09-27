package infra;

import domain.cube.CubeBank;
import domain.cure.CureMarkerArea;
import domain.game.Game;
import domain.infection.CityInfector;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.outbreak.OutbreakDetector;
import domain.infection.outbreak.OutbreakPropagator;
import domain.infection.outbreak.OutbrokenCityFinder;
import domain.network.Network;
import domain.treatment.Treatment;

public class World {

    public static EventBus eventBus;
    public static OutbrokenCityFinder outbrokenCityFinder = new EventSourcingOutbrokenCityDao();
    public static Game game;

    public static void create() {
        eventBus = new EventBus();
        CureMarkerArea cureMarkerArea = new CureMarkerArea();
        OutbreakCounter outbreakCounter = new OutbreakCounter();
        game = new Game(new Network(), new CubeBank(), outbreakCounter, cureMarkerArea);
        eventBus.listenNoAvailableCubeLeft(game);
        eventBus.listenAllDiseasesCured(game);
        eventBus.listenMax(game);
        eventBus.listenInfection(new CityInfector());
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(outbreakCounter);
        eventBus.listenTreatment(new Treatment());
        eventBus.listenCureDiscovering(cureMarkerArea);
    }

}
