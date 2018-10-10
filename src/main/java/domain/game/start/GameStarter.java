package domain.game.start;

import domain.board.Board;
import domain.game.Game;
import domain.game.Players;
import domain.infection.cards.InfectionCard;
import domain.network.CityName;

public class GameStarter {

    private final Dealer dealer;
    private final Board board;
    private final Players players;
    private Game.Level level;

    public GameStarter(Board board, Players players, Game.Level level) {
        this.board = board;
        this.players = players;
        this.level = level;
        this.dealer = new Dealer();
    }

    public void deal() {
        dealer.deal(players);
    }

    public void putInitialDiseaseCubesOnTheBoard() {
        putDefaultColorCubesOnThreeFirstDrawnInfectionCards(3);
        putDefaultColorCubesOnThreeFirstDrawnInfectionCards(2);
        putDefaultColorCubesOnThreeFirstDrawnInfectionCards(1);
    }

    private void putDefaultColorCubesOnThreeFirstDrawnInfectionCards(int nbCubes) {
        for (int i = 0; i < 3; i++) {
            InfectionCard draw = board.infectionCardsPiles.pop();
            for (int j = 0; j < nbCubes; j++) {
                board.network.get(CityName.valueOf(draw.name())).putCube(draw.disease());
            }
        }
    }

    public void addEpidemicCardsToDrawPile() {
        board.playerCardsPiles.addEpidemicCardsToDrawPile(level.nbEpidemicCard);
    }
}
