package domain.game;

import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import infra.World;


public class Game implements NoAvailableCubeLeftListener {

    @Override
    public void onNoAvailableCubeLeft(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent) {
        World.gameState = GameState.LOST;
    }
}
