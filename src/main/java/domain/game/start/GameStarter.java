package domain.game.start;

import domain.game.Game;
import domain.infection.cards.InfectionCard;
import domain.network.CityName;

public class GameStarter {

    private final Game game;
    private final Dealer dealer;

    public GameStarter(Game game) {
        this.game = game;
        this.dealer = new Dealer();
    }

    public void deal() {
        dealer.deal(game.players);
    }

    public void putInitialDiseaseCubesOnTheBoard() {
        putDefaultColorCubesOnThreeFirstDrawnInfectionCards(3);
        putDefaultColorCubesOnThreeFirstDrawnInfectionCards(2);
        putDefaultColorCubesOnThreeFirstDrawnInfectionCards(1);
    }

    private void putDefaultColorCubesOnThreeFirstDrawnInfectionCards(int nbCubes) {
        for (int i = 0; i < 3; i++) {
            InfectionCard draw = game.infectionCardsPiles.pop();
            for (int j = 0; j < nbCubes; j++) {
                game.network.get(CityName.valueOf(draw.name())).putCube(draw.disease());
            }
        }
    }

    public void addEpidemicCardsToDrawPile() {
        game.playerCardsPiles.addEpidemicCardsToDrawPile(game.level.nbEpidemicCard);
    }
}
