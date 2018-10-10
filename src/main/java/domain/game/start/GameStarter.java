package domain.game.start;

import domain.board.Board;
import domain.game.Game;
import domain.game.Players;
import domain.infection.cards.InfectionCard;
import domain.network.CityName;
import domain.player.cards.PlayerCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStarter {

    private final Board board;
    private final Players players;
    private Game.Level level;

    public GameStarter(Board board, Players players, Game.Level level) {
        this.board = board;
        this.players = players;
        this.level = level;
    }

    public void deal() {
        new Dealer().deal(players);
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
        divideDrawPileIntoEqualParts(level.nbEpidemicCard).forEach(this::addRandomlyAnEpidemicCard);
    }

    private void addRandomlyAnEpidemicCard(List<PlayerCard> deck) {
        deck.add(new Random().nextInt(deck.size()), PlayerCard.EPIDEMIC);
        putBackIntoDrawPile(deck);
    }

    private void putBackIntoDrawPile(List<PlayerCard> partition) {
        board.playerCardsPiles.drawPile().addAll(partition);
    }

    private List<List<PlayerCard>> divideDrawPileIntoEqualParts(int nbEpidemicCards) {
        List<List<PlayerCard>> lists = splitInto(board.playerCardsPiles.drawPile(), nbEpidemicCards, new ArrayList<>());
        board.playerCardsPiles.drawPile().clear();
        return lists;
    }

    private List<List<PlayerCard>> splitInto(List<PlayerCard> list, int numberOfParts, List<List<PlayerCard>> result) {
        if (numberOfParts == 0) {
            return result;
        }
        int ceil = (int) Math.ceil(((double) list.size()) / numberOfParts);
        result.add(new ArrayList<>(list.subList(0, ceil)));
        return splitInto(new ArrayList<>(list.subList(ceil, list.size())), numberOfParts - 1, result);
    }

    public void start(CityName researchStationLocation, CityName pawnLocation) {
        deal();
        addEpidemicCardsToDrawPile();
        putInitialDiseaseCubesOnTheBoard();
        board.researchStations.buildOn(researchStationLocation);
        players.get().forEach((p) -> board.locations.move(p.role(), pawnLocation));
    }
}
