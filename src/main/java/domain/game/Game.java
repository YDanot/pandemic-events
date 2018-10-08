package domain.game;

import com.google.common.collect.Lists;
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
import domain.treatment.cure.AllDiseaseCuredEvent;
import domain.treatment.cure.AllDiseasesCuredListener;
import domain.treatment.cure.CureMarkerArea;


public class Game implements NoAvailableCubeLeftListener, AllDiseasesCuredListener, MaxOutbreakNumberReachedListener {

    public final Network network;
    public final CubeBank cubeBank;
    public final OutbreakCounter outbreakCounter;
    public final CureMarkerArea cureMarkerArea;
    public final InfectionCardsPiles infectionCardsPiles;
    public final InfectionRateTrack infectionRateTrack;
    public final Players players;
    public final Level level;
    public final PlayerHands playerHands;
    public GameState gameState = GameState.AVAILABLE;
    public PawnLocations locations;
    public ResearchStations researchStations;
    public PlayerCardsPiles playerCardsPiles;

    public Game(Network network, CubeBank cubeBank, OutbreakCounter outbreakCounter, CureMarkerArea cureMarkerArea, PawnLocations locations, ResearchStations researchStations, InfectionCardsPiles infectionCardsPiles, InfectionRateTrack infectionRateTrack, PlayerCardsPiles playerCardsPiles, Players players, Level level) {
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
        this.level = level;
        if (players != null)
            this.playerHands = PlayerHands.build(players.get());
        else
            playerHands = PlayerHands.build(Lists.newArrayList());
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

    public enum Level {
        INTRODUCTION(4),
        NORMAL(5),
        HEROIC(6);

        public int nbEpidemicCard;

        Level(int nbEpidemicCard) {
            this.nbEpidemicCard = nbEpidemicCard;
        }
    }

}
