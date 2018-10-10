package domain.game.start;

import domain.game.Players;
import infra.World;

public class Dealer {

    public void deal(Players players) {
        deal(players, computeNbCardsToDeal(players));
    }

    private void deal(Players players, int nbCardByPlayer) {
        int turn = 0;
        do {
            dealOneCardTo(players);
            turn++;
        } while (turn < nbCardByPlayer);
    }

    private void dealOneCardTo(Players players) {
        players.get().forEach(player -> World.game.playerHands.deal(player, World.board.playerCardsPiles.draw()));
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
