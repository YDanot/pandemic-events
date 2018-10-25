package domain.game;

import domain.actions.Action;
import domain.player.cards.PlayerCard;
import infra.World;

public class Turn {

    private TurnId turnId;
    private Player player;
    private int actionCounter = 4;
    private int nbPlayerCardToDraw = 2;
    private int nbInfectionCardToDraw = World.board.infectionRateTrack.current();
    private boolean actionPhasePassed = false;

    Turn(Player player) {
        this.turnId = new TurnId();
        this.player = player;
    }

    public TurnId id() {
        return turnId;
    }

    public void take(Action action) {
        player.take(action);
        actionCounter--;
    }

    public void pass() {
        actionPhasePassed = true;
    }

    public void drawAPlayerCard() {
        if (!takingActionPhaseDone()) {
            throw new IllegalStateException();
        }
        PlayerCard draw = World.board.playerCardsPiles.draw(id());
        if (draw.equals(PlayerCard.EPIDEMIC)) {
            World.board.playerCardsPiles.discard(draw);
        } else {
            World.game.playerHands.handOf(player).deal(draw);
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
        return nbPlayerCardToDraw == 0 && World.game.playerHands.handOf(player).get().size() <= 7;
    }

    private boolean infectorPhaseDone() {
        return nbInfectionCardToDraw == 0;
    }

    public boolean isOver() {
        return infectorPhaseDone() && takingActionPhaseDone() && drawingPhaseDone();
    }


    public Player player() {
        return player;
    }
}
