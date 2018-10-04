package domain.game;

import domain.actions.basics.PawnLocations;
import domain.cube.CubeBank;
import domain.cube.NoAvailableCubeLeftEvent;
import domain.cube.NoAvailableCubeLeftListener;
import domain.infection.cards.InfectionCardsPiles;
import domain.infection.outbreak.MaxOutbreakNumberReachedListener;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.rate.InfectionRateTrack;
import domain.network.Network;
import domain.player.cards.PlayerCardsPiles;
import domain.player.cards.PlayerHands;
import domain.researchstation.ResearchStations;
import domain.role.Role;
import domain.treatment.cure.AllDiseaseCuredEvent;
import domain.treatment.cure.AllDiseasesCuredListener;
import domain.treatment.cure.CureMarkerArea;

import java.util.List;


public class Game implements NoAvailableCubeLeftListener, AllDiseasesCuredListener, MaxOutbreakNumberReachedListener {

    public final Network network;
    public final CubeBank cubeBank;
    public final OutbreakCounter outbreakCounter;
    public final CureMarkerArea cureMarkerArea;
    public final InfectionCardsPiles infectionCardsPiles;
    public final InfectionRateTrack infectionRateTrack;
    public final List<Role> players;
    public GameState gameState = GameState.AVAILABLE;
    public PawnLocations locations;
    public PlayerHands playerHands = new PlayerHands();
    public ResearchStations researchStations;
    public PlayerCardsPiles playerCardsPiles;

    public Game(Network network, CubeBank cubeBank, OutbreakCounter outbreakCounter, CureMarkerArea cureMarkerArea, PawnLocations locations, ResearchStations researchStations, InfectionCardsPiles infectionCardsPiles, InfectionRateTrack infectionRateTrack, PlayerCardsPiles playerCardsPiles, List<Role> players) {
        this.network = network;
        this.cubeBank = cubeBank;
        this.outbreakCounter = outbreakCounter;
        this.cureMarkerArea = cureMarkerArea;
        this.locations = locations;
        this.researchStations = researchStations;
        this.infectionCardsPiles = infectionCardsPiles;
        this.infectionRateTrack = infectionRateTrack;
        this.playerCardsPiles = playerCardsPiles;
        this.players = players;
    }

    @Override
    public void onNoAvailableCubeLeft(NoAvailableCubeLeftEvent noAvailableCubeLeftEvent) {
        gameState = GameState.LOST;
    }

    @Override
    public void onAllDiseasesCured(AllDiseaseCuredEvent event) {
        gameState = GameState.WON;
    }

    @Override
    public void onMaxOutbreakNumberReached() {
        gameState = GameState.LOST;
    }

    public void start() {
        int nbCardsDealAtBegining = 2;
        for (int i = 1; i <= players.size() * nbCardsDealAtBegining; i++) {
            playerHands.deal(players.get(i % players.size()), playerCardsPiles.draw());
        }
    }
}
