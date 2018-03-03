package infra;

import domain.board.Network;
import domain.cube.CubeBank;
import domain.game.Game;
import domain.game.GameState;
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
    public static CubeBank cubeBank;
    public static GameState gameState;

    public static void create() {
        gameState = GameState.AVAILABLE;
        eventBus = new EventBus();
        network = new Network();
        cubeBank = new CubeBank();
        outbreakCounter = new OutbreakCounter();
        eventBus.listenNoAvailableCubeLeft(new Game());
        eventBus.listenInfection(new CityInfector());
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(outbreakCounter);
    }

}
