package domain.game.start;

import domain.game.Players;
import domain.game.TurnId;
import infra.World;

public class Dealer {

    public void deal(Players players, TurnId turnId) {
        deal(players, computeNbCardsToDeal(players), turnId);
    }

    private void deal(Players players, int nbCardByPlayer, TurnId turnId) {
        int turn = 0;
        do {
            dealOneCardTo(players, turnId);
            turn++;
        } while (turn < nbCardByPlayer);
    }

    private void dealOneCardTo(Players players, TurnId turnId) {
        players.get().forEach(player -> World.game.playerHands.deal(player, World.board.playerCardsPiles.draw(turnId)));
    }


    private int computeNbCardsToDeal(Players players) {
        int nbPlayers = players.count();

        if (nbPlayers == 2) {
            return 4;
        }

        if (nbPlayers == 3) {
            return 3;
        }

        return 2;
    }
}
