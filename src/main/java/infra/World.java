package infra;

import domain.board.Board;
import domain.board.PawnLocations;
import domain.cube.CubeBank;
import domain.epidemic.Epidemic;
import domain.game.Game;
import domain.game.Player;
import domain.game.Players;
import domain.infection.CityInfector;
import domain.infection.Infector;
import domain.infection.cards.InfectionCardsPiles;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.outbreak.OutbreakDetector;
import domain.infection.outbreak.OutbreakPropagator;
import domain.infection.rate.InfectionRateTrack;
import domain.network.CityName;
import domain.network.Network;
import domain.player.cards.PlayerCardsPiles;
import domain.researchstation.ResearchStations;
import domain.role.Role;
import domain.treatment.Treatment;
import domain.treatment.cure.CureMarkerArea;

public class World {

    public static EventBus eventBus;
    public static Game game;
    public static Board board;

    public static void create() {
        Players players = Players.of(Player.as(Role.SCIENTIST), Player.as(Role.MEDIC));
        Board board = new Board(new Network(), new CubeBank(), new OutbreakCounter(), new CureMarkerArea(), new InfectionCardsPiles(), new InfectionRateTrack(), new PawnLocations(CityName.PARIS, players), new ResearchStations(CityName.PARIS), new PlayerCardsPiles());
        create(board, players);
    }

    public static void create(Board board, Players players) {
        World.game = new Game(players);
        World.board = board;
        eventBus = new SyncEventBus();
        eventBus.listenEpidemic(new Epidemic());
        eventBus.listenTakeCube(World.board.cubeBank);
        eventBus.listenNoAvailableCubeLeft(World.game);
        eventBus.listenAllDiseasesCured(World.game);
        eventBus.listenMax(World.game);
        eventBus.listenEradication(World.board.cureMarkerArea);
        eventBus.listenInfectionCardDrawn(new Infector());
        eventBus.listenInfection(new CityInfector());
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(World.board.outbreakCounter);
        eventBus.listenTreatment(new Treatment());
        eventBus.listenCureDiscovering(World.board.cureMarkerArea);
    }

}
