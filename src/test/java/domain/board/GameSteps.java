package domain.board;

import cucumber.api.java.en.*;
import domain.actions.basics.DriveOrFerry;
import domain.cube.CubeBank;
import domain.game.*;
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
import domain.role.Role;
import domain.treatment.cure.CureMarkerArea;
import infra.World;
import run.AsyncAssertions;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static domain.role.Role.MEDIC;
import static domain.role.Role.SCIENTIST;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;


public class GameSteps {

    private final CitySteps citySteps;
    private Game.Level level;
    private Turn currentTurn;

    public GameSteps(CitySteps citySteps) {
        this.citySteps = citySteps;
    }

    @Then("^game should be (available|lost|won)")
    public void gameShouldBeAvailable(GameState gameState) throws Throwable {
        boolean validated = AsyncAssertions.isTrueWithin(() ->
                        World.game.gameState == gameState,
                1, TimeUnit.SECONDS);
        assertThat(validated).as("game state should be " + gameState).isTrue();
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
        setupGame(Players.of(Player.as(SCIENTIST), Player.as(MEDIC)));
    }

    private void setupGame(Players players) throws Throwable {
        Board standard = standardBoard();
        World.create(standard, players);
        levelIs(Game.Level.INTRODUCTION);
    }

    @Given("^a standard game$")
    public void aStandardGame() throws Throwable {
        aStartingStandardGame();
        start();
    }

    @Given("^the game has been started$")
    public void start() {
        new GameStarter(World.board, World.game.players, level)
                .start(CityName.ATLANTA, CityName.ATLANTA);
        currentTurn = World.game.players.newTurn();
    }

    @And("^Level is (.*)")
    public void levelIs(Game.Level level) throws Throwable {
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

    @And("^(?:Scientist|Medic) has driven to (.*)")
    public void scientistHasDrivenFromAtlantaToWashington(CityName destination) throws Throwable {
        currentTurn.take(new DriveOrFerry(destination));
    }

    @Then("^(Scientist|Medic) should be able to take an action$")
    public void scientistShouldBeAbleToTakeAnAction(Role role) throws Throwable {
        assertThat(currentTurn.player().equals(Player.as(role)) || !currentTurn.takingActionPhaseDone()).isTrue();
    }

    @Then("^(Scientist|Medic) should not be able to take an action$")
    public void scientistShouldNotBeAbleToTakeAnAction(Role role) throws Throwable {
        assertThat(!currentTurn.player().equals(Player.as(role)) || currentTurn.takingActionPhaseDone()).isTrue();
    }

    @When("^(?:Scientist|Medic) draws a card$")
    public void scientistDrawsACard() throws Throwable {
        currentTurn.drawAPlayerCard();
    }

    @Then("^(?:Scientist|Medic) should not be able to draw (?:another|a) card$")
    public void shouldBeAbleToDrawAnOtherCard() throws Throwable {
        assertThat(currentTurn.drawingPhaseDone()).isTrue();
    }

    @And("^(?:Scientist|Medic) has drawn a card$")
    public void scientistHasDrawnACard() throws Throwable {
        currentTurn.drawAPlayerCard();
    }

    @And("^(?:Scientist|Medic) has drawn 2 cards$")
    public void scientistHasDrawnCards() throws Throwable {
        currentTurn.drawAPlayerCard();
        currentTurn.drawAPlayerCard();
    }

    @When("^infector plays$")
    public void infectorPlays() throws Throwable {
        currentTurn.infectorPhase();
    }

    @Then("^Turn should be over$")
    public void turnShouldBeOver() throws Throwable {
        assertThat(currentTurn.isOver()).isTrue();
    }

    @Then("^drawing phase should not be over$")
    public void drawingPhaseShouldNotBeOver() throws Throwable {
        assertThat(currentTurn.drawingPhaseDone()).isFalse();
    }

    @And("^(?:Scientist|Medic) has taken 4 actions$")
    public void scientistHasTakenActions() throws Throwable {
        currentTurn.take(new DriveOrFerry(CityName.WASHINGTON));
        currentTurn.take(new DriveOrFerry(CityName.MONTREAL));
        currentTurn.take(new DriveOrFerry(CityName.NEW_YORK));
        currentTurn.take(new DriveOrFerry(CityName.LONDON));
    }

    @Then("^infection should occurs on (.*)")
    public void infectionShouldOccursOnJakarta(CityName cityName) throws Throwable {
        assertThat(World.eventBus.getInfectionEvents().stream().anyMatch((e) -> e.cityName.equals(cityName) && e.turnId.equals(currentTurn.id())));
    }

    @When("^(?:Scientist|Medic) pass his turn$")
    public void scientistPassHisTurn() throws Throwable {
        currentTurn.pass();
    }

    private Board standardBoard() {
        return new Board(
                citySteps.theStandardNetwork(),
                new CubeBank(),
                new OutbreakCounter(),
                new CureMarkerArea(),
                new InfectionCardsPiles(),
                new InfectionRateTrack(),
                new PawnLocations(),
                new ResearchStations(),
                new PlayerCardsPiles());
    }

    @And("^Players should be (.*)$")
    public void playersShouldBe(List<Role> expectedRoles) throws Throwable {
        List<Role> roles = World.game.players.get().stream().map(Player::role).collect(toList());
        Collections.sort(roles);
        Collections.sort(expectedRoles);
        assertThat(expectedRoles).containsExactlyElementsOf(roles);
    }

    @And("^Game level should be (.*)$")
    public void gameLevelShouldBe(Game.Level level) throws Throwable {
        assertThat(this.level).isEqualTo(level);
    }

    @And("^Players should start in (.*)$")
    public void playersShouldStartInAtlanta(CityName expectedStartingCity) throws Throwable {
        World.game.players.get().stream().map(Player::role)
                .forEach((r) -> assertThat(World.board.locations.locationsOf(r)).isEqualTo(expectedStartingCity));
    }

    @And("^it should be the turn of (.*)$")
    public void itShouldBeTheTurnOfScientist(Role expectedRole) throws Throwable {
        assertThat(currentTurn.player()).isEqualTo(Player.as(expectedRole));
    }

    @But("^(.*) plays instead of (.*)$")
    public void dispatcherPlaysInsteadOfScientist(Role player, Role oldPlayer) throws Throwable {
        setupGame(Players.of(Player.as(SCIENTIST), Player.as(MEDIC)));
    }

    @But("^(.*) are playing$")
    public void scientistDispatcherArePlaying(List<Role> roles) throws Throwable {
        setupGame(new Players(roles.stream().map(Player::as).collect(toList())));
        start();
    }

    @And("^it is the turn of (.*)$")
    public void itIsTheTurnOfDispatcher(Role role) throws Throwable {
        while (!currentTurn.player().role().equals(role)) {
            currentTurn = World.game.players.newTurn();
        }
    }
}
