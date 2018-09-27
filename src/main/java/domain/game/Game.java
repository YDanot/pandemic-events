package domain.game;

import domain.cube.CubeBank;
import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.infection.outbreak.MaxOutbreakNumberReachedListener;
import domain.infection.outbreak.OutbreakCounter;
import domain.network.Network;
import domain.treatment.cure.AllDiseaseCuredEvent;
import domain.treatment.cure.AllDiseasesCuredListener;
import domain.treatment.cure.CureMarkerArea;


public class Game implements NoAvailableCubeLeftListener, AllDiseasesCuredListener, MaxOutbreakNumberReachedListener {

    public final Network network;
    public final CubeBank cubeBank;
    public final OutbreakCounter outbreakCounter;
    public final CureMarkerArea cureMarkerArea;
    public GameState gameState = GameState.AVAILABLE;

    public Game(Network network, CubeBank cubeBank, OutbreakCounter outbreakCounter, CureMarkerArea cureMarkerArea) {
        this.network = network;
        this.cubeBank = cubeBank;
        this.outbreakCounter = outbreakCounter;
        this.cureMarkerArea = cureMarkerArea;
    }

    @Override
    public void onNoAvailableCubeLeft(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent) {
        gameState = GameState.LOST;
    }

    @Override
    public void onAllDiseasesCured(AllDiseaseCuredEvent event) {
        gameState = GameState.WON;
    }

    @Override
    public void onMaxOutbreakNumberReached() {
        gameState = GameState.LOST;
    }
}
