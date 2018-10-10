package domain.board;

import domain.cube.CubeBank;
import domain.infection.cards.InfectionCardsPiles;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.rate.InfectionRateTrack;
import domain.network.Network;
import domain.player.cards.PlayerCardsPiles;
import domain.researchstation.ResearchStations;
import domain.treatment.cure.CureMarkerArea;

public class Board {

    public final Network network;
    public final CubeBank cubeBank;
    public final OutbreakCounter outbreakCounter;
    public final CureMarkerArea cureMarkerArea;
    public final InfectionCardsPiles infectionCardsPiles;
    public final InfectionRateTrack infectionRateTrack;
    public PawnLocations locations;
    public ResearchStations researchStations;
    public PlayerCardsPiles playerCardsPiles;


    public Board(Network network, CubeBank cubeBank, OutbreakCounter outbreakCounter, CureMarkerArea cureMarkerArea, InfectionCardsPiles infectionCardsPiles, InfectionRateTrack infectionRateTrack, PawnLocations locations, ResearchStations researchStations, PlayerCardsPiles playerCardsPiles) {
        this.network = network;
        this.cubeBank = cubeBank;
        this.outbreakCounter = outbreakCounter;
        this.cureMarkerArea = cureMarkerArea;
        this.infectionCardsPiles = infectionCardsPiles;
        this.infectionRateTrack = infectionRateTrack;
        this.locations = locations;
        this.researchStations = researchStations;
        this.playerCardsPiles = playerCardsPiles;
    }

    public static Board standard() {
        return new Board(
                Network.standard(),
                new CubeBank(),
                new OutbreakCounter(),
                new CureMarkerArea(),
                new InfectionCardsPiles(),
                new InfectionRateTrack(),
                new PawnLocations(),
                new ResearchStations(),
                new PlayerCardsPiles());
    }
}
