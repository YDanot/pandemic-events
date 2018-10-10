package domain.game;

import com.google.common.collect.Lists;
import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.infection.outbreak.MaxOutbreakNumberReachedListener;
import domain.player.cards.PlayerHands;
import domain.treatment.cure.AllDiseaseCuredEvent;
import domain.treatment.cure.AllDiseasesCuredListener;


public class Game implements NoAvailableCubeLeftListener, AllDiseasesCuredListener, MaxOutbreakNumberReachedListener {

    public final Players players;
    public final Level level;
    public final PlayerHands playerHands;
    public GameState gameState = GameState.AVAILABLE;

    public Game(Players players, Level level) {
        this.players = players;
        this.level = level;
        if (players != null)
            this.playerHands = PlayerHands.build(players.get());
        else
            playerHands = PlayerHands.build(Lists.newArrayList());
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

    public enum Level {
        INTRODUCTION(4),
        NORMAL(5),
        HEROIC(6);

        public int nbEpidemicCard;

        Level(int nbEpidemicCard) {
            this.nbEpidemicCard = nbEpidemicCard;
        }
    }

}
