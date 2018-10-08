package domain.board;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.basics.PawnLocations;
import domain.cube.CubeBank;
import domain.game.Game;
import domain.game.GameState;
import domain.game.Player;
import domain.game.Players;
import domain.game.start.Dealer;
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

import static domain.network.CityName.ATLANTA;
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
        citySteps.the_occident_sub_network();

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

        World.create(new Game(World.game.network, new CubeBank(), new OutbreakCounter(), new CureMarkerArea(), pawnLocations, researchStations, infectionCardsPiles, new InfectionRateTrack(), playerCardsPiles,
                players, Game.Level.INTRODUCTION));
    }

    @Given("^a standard game$")
    public void aStandardGame() throws Throwable {
        Network network = citySteps.standard_network();

        Players players = Players.of(Player.as(SCIENTIST), Player.as(MEDIC));

        PawnLocations pawnLocations = new PawnLocations(ATLANTA, players);
        ResearchStations researchStations = new ResearchStations(ATLANTA);
        World.create(new Game(network, new CubeBank(), new OutbreakCounter(), new CureMarkerArea(), pawnLocations, researchStations, new InfectionCardsPiles(), new InfectionRateTrack(), new PlayerCardsPiles(),
                players, Game.Level.INTRODUCTION));
    }

    @And("^Level is (.*)")
    public void levelIsIntroduction(Game.Level level) throws Throwable {
        this.level = level;
    }

    @And("^cards has been dealt$")
    public void cardsHasBeenDealt() throws Throwable {
        new Dealer().deal(World.game.players);
    }

    @When("^we deal cards$")
    public void deal() throws Throwable {
        new Dealer().deal(World.game.players);
    }

    @When("^we put initial disease cubes on the board$")
    public void wePutInitialDiseaseCubesOnTheBoard() throws Throwable {
        new GameStarter(World.game).putInitialDiseaseCubesOnTheBoard();
    }
}
