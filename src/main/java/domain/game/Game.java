package domain.game;

import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.cure.AllDiseaseCuredEvent;
import domain.cure.AllDiseasesCuredListener;
import domain.infection.outbreak.MaxOutbreakNumberReachedListener;
import infra.World;


public class Game implements NoAvailableCubeLeftListener, AllDiseasesCuredListener, MaxOutbreakNumberReachedListener {

    @Override
    public void onNoAvailableCubeLeft(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent) {
        World.gameState = GameState.LOST;
    }

    @Override
    public void onAllDiseasesCured(AllDiseaseCuredEvent event) {
        World.gameState = GameState.WON;
    }

    @Override
    public void onMaxOutbreakNumberReached() {
        World.gameState = GameState.LOST;
    }
}
