package domain.board;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.cube.CubeBank;
import domain.game.Game;
import domain.game.GameState;
import domain.game.Player;
import domain.game.Players;
import domain.game.start.GameStarter;
import domain.infection.cards.InfectionCard;
import domain.infection.cards.InfectionCardsPiles;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.rate.InfectionRateTrack;
import domain.network.CityName;
import domain.network.Network;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerCardsPiles;
import domain.researchstation.ResearchStations;
import domain.treatment.cure.CureMarkerArea;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.concurrent.TimeUnit;

import static domain.role.Role.MEDIC;
import static domain.role.Role.SCIENTIST;


public class GameSteps {

    private final CitySteps citySteps;
    private Game.Level level;

    public GameSteps(CitySteps citySteps) {
        this.citySteps = citySteps;
    }

    @Then("^game should be (available|lost|won)")
    public void gameShouldBeAvailable(GameState gameState) throws Throwable {
        boolean validated = AsyncAssertions.isTrueWithin(() ->
                        World.game.gameState == gameState,
                1, TimeUnit.SECONDS);
        Assertions.assertThat(validated).as("game state should be " + gameState).isTrue();
    }

    @Given("^a minimalist game$")
    public void a_minimalist_game() throws Throwable {
        Network network = citySteps.the_occident_sub_network();

        InfectionCardsPiles infectionCardsPiles = new InfectionCardsPiles();
        infectionCardsPiles.drawPile().clear();
        infectionCardsPiles.drawPile().add(InfectionCard.PARIS);
        infectionCardsPiles.drawPile().add(InfectionCard.LONDON);
        infectionCardsPiles.drawPile().add(InfectionCard.MADRID);
        infectionCardsPiles.drawPile().add(InfectionCard.MILAN);
        infectionCardsPiles.drawPile().add(InfectionCard.ALGIERS);
        infectionCardsPiles.drawPile().add(InfectionCard.ESSEN);
        infectionCardsPiles.drawPile().add(InfectionCard.NEW_YORK);

        PlayerCardsPiles playerCardsPiles = new PlayerCardsPiles();
        playerCardsPiles.drawPile().clear();
        playerCardsPiles.drawPile().add(PlayerCard.PARIS);
        playerCardsPiles.drawPile().add(PlayerCard.LONDON);
        playerCardsPiles.drawPile().add(PlayerCard.MADRID);
        playerCardsPiles.drawPile().add(PlayerCard.MILAN);
        playerCardsPiles.drawPile().add(PlayerCard.ALGIERS);
        playerCardsPiles.drawPile().add(PlayerCard.ESSEN);
        playerCardsPiles.drawPile().add(PlayerCard.NEW_YORK);

        Players players = Players.of(Player.as(SCIENTIST), Player.as(MEDIC));

        PawnLocations pawnLocations = new PawnLocations(CityName.PARIS, players);
        ResearchStations researchStations = new ResearchStations(CityName.PARIS);

        Board board = new Board(network, new CubeBank(), new OutbreakCounter(), new CureMarkerArea(), infectionCardsPiles, new InfectionRateTrack(), pawnLocations, researchStations, playerCardsPiles);
        World.create(board, players);
    }

    @Given("^a starting standard game$")
    public void aStartingStandardGame() throws Throwable {
        Players players = Players.of(Player.as(SCIENTIST), Player.as(MEDIC));
        Board standard = Board.standard();
        World.create(standard, players);
    }

    @Given("^a standard game$")
    public void aStandardGame() throws Throwable {
        Players players = Players.of(Player.as(SCIENTIST), Player.as(MEDIC));
        Board standard = Board.standard();
        World.create(standard, players);
        new GameStarter(World.board, World.game.players, Game.Level.INTRODUCTION).start(CityName.ATLANTA, CityName.ATLANTA);
    }

    @And("^Level is (.*)")
    public void levelIsIntroduction(Game.Level level) throws Throwable {
        this.level = level;
    }

    @And("^cards has been dealt$")
    public void cardsHasBeenDealt() throws Throwable {
        starter().deal();
    }

    @When("^we deal cards$")
    public void deal() throws Throwable {
        starter().deal();
    }

    @When("^we put initial disease cubes on the board$")
    public void wePutInitialDiseaseCubesOnTheBoard() throws Throwable {
        starter().putInitialDiseaseCubesOnTheBoard();
    }

    @When("^we add Epidemic cards to draw Pile$")
    public void weAddEpidemicCardsToDrawPile() throws Throwable {
        starter().addEpidemicCardsToDrawPile();
    }

    private GameStarter starter() {
        return new GameStarter(World.board, World.game.players, level);
    }
}
