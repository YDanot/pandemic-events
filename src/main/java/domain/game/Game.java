package domain.game;

import com.google.common.collect.Lists;
import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.infection.outbreak.MaxOutbreakNumberReachedListener;
import domain.network.CityName;
import domain.player.cards.PlayerHand;
import domain.player.cards.PlayerHands;
import domain.role.Role;
import domain.treatment.cure.AllDiseaseCuredEvent;
import domain.treatment.cure.AllDiseasesCuredListener;
import infra.World;


public class Game implements NoAvailableCubeLeftListener, AllDiseasesCuredListener, MaxOutbreakNumberReachedListener {

    public final PlayerHands playerHands;
    public Players players;
    public GameState gameState = GameState.AVAILABLE;

    public Game(Players players) {
        this.players = players;
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

    public boolean isTurnOf(Role role) {
        return players.currentTurn().player().role() == role;
    }

    public PossibleActions possibleActions() {
        if (players.currentTurn().gameplay().takingActionPhaseDone()) {
            return PossibleActions.NONE;
        }
        CityName locationsOfCurrentPlayer = World.board.locations.locationsOf(players.currentTurn().player().role());
        PlayerHand currentPlayerHand = World.game.playerHands.handOf(players.currentTurn().player());
        Role role = players.currentTurn().player().role();
        return new PossibleActions(locationsOfCurrentPlayer, currentPlayerHand, role);
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
