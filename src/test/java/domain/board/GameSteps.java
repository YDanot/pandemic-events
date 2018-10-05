package domain.board;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.actions.basics.PawnLocations;
import domain.cube.CubeBank;
import domain.game.Game;
import domain.game.GameState;
import domain.infection.cards.InfectionCard;
import domain.infection.cards.InfectionCardsPiles;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.rate.InfectionRateTrack;
import domain.network.CityName;
import domain.player.cards.PlayerCard;
import domain.player.cards.PlayerCardsPiles;
import domain.researchstation.ResearchStations;
import domain.role.Role;
import domain.treatment.cure.CureMarkerArea;
import infra.World;
import org.assertj.core.api.Assertions;
import run.AsyncAssertions;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class GameSteps {

    private final CitySteps citySteps;

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

        Set<Role> players = new HashSet<>();
        players.add(Role.SCIENTIST);
        players.add(Role.MEDIC);

        PawnLocations pawnLocations = new PawnLocations(CityName.PARIS, players);
        ResearchStations researchStations = new ResearchStations(CityName.PARIS);

        World.create(new Game(World.game.network, new CubeBank(), new OutbreakCounter(), new CureMarkerArea(), pawnLocations, researchStations, infectionCardsPiles, new InfectionRateTrack(), playerCardsPiles,
                players));
    }

    @When("^the game starts with (\\d+) epidemic cards$")
    public void theGameStartsWithEpidemicCards(int nbCards) throws Throwable {
        World.game.start(World.game.players.size(), nbCards);
    }

    @Given("^a standard game$")
    public void aStandardGame() throws Throwable {

    }
}
