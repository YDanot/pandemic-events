package domain.game;

import domain.actions.Action;
import domain.actions.ActionImpossible;
import domain.player.cards.PlayerCard;
import infra.World;

import java.util.Optional;

public class Gameplay {

    private Turn turn;
    private int actionCounter = 4;
    private int nbPlayerCardToDraw = 2;
    private int nbInfectionCardToDraw = World.board.infectionRateTrack.current();
    private boolean actionPhasePassed = false;

    Gameplay(Turn turn) {
        this.turn = turn;
    }

    public Optional<ActionImpossible> take(Action action) {
        Optional<ActionImpossible> take = turn.player().take(action);
        actionCounter--;
        return take;
    }

    public void pass() {
        actionPhasePassed = true;
    }

    public void drawAPlayerCard() {
        if (!takingActionPhaseDone()) {
            throw new IllegalStateException();
        }
        PlayerCard draw = World.board.playerCardsPiles.draw(turn.id());
        if (draw.equals(PlayerCard.EPIDEMIC)) {
            World.board.playerCardsPiles.discard(draw);
        } else {
            World.game.playerHands.handOf(turn.player()).deal(draw);
        }
        nbPlayerCardToDraw--;
    }

    public void infectorPhase() {
        if (!drawingPhaseDone()) {
            throw new IllegalStateException();
        }
        while (nbInfectionCardToDraw > 0) {
            World.board.infectionCardsPiles.draw();
            nbInfectionCardToDraw--;
        }
    }

    public boolean takingActionPhaseDone() {
        return actionCounter == 0 || actionPhasePassed;
    }

    public boolean drawingPhaseDone() {
        return nbPlayerCardToDraw == 0 && World.game.playerHands.handOf(turn.player()).get().size() <= 7;
    }

    private boolean infectorPhaseDone() {
        return nbInfectionCardToDraw == 0;
    }

    public boolean isOver() {
        return infectorPhaseDone() && takingActionPhaseDone() && drawingPhaseDone();
    }
}
