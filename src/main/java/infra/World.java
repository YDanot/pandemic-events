package infra;

import domain.actions.basics.PawnLocations;
import domain.cube.CubeBank;
import domain.epidemic.Epidemic;
import domain.game.Game;
import domain.infection.CityInfector;
import domain.infection.Infector;
import domain.infection.cards.InfectionCardsPiles;
import domain.infection.outbreak.OutbreakCounter;
import domain.infection.outbreak.OutbreakDetector;
import domain.infection.outbreak.OutbreakPropagator;
import domain.infection.outbreak.OutbrokenCityFinder;
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
    public static OutbrokenCityFinder outbrokenCityFinder = new EventSourcingOutbrokenCityDao();
    public static Game game;
    private static CityInfector cityInfector;

    public static void create() {
        eventBus = new SyncEventBus();
        CureMarkerArea cureMarkerArea = new CureMarkerArea();
        OutbreakCounter outbreakCounter = new OutbreakCounter();
        CubeBank cubeBank = new CubeBank();
        eventBus.listenEpidemic(new Epidemic());
        game = new Game(new Network(), cubeBank, outbreakCounter, cureMarkerArea, new PawnLocations(CityName.PARIS, Role.values()), new ResearchStations(CityName.PARIS), new InfectionCardsPiles(), new InfectionRateTrack(), new PlayerCardsPiles());
        eventBus.listenTakeCube(cubeBank);
        eventBus.listenNoAvailableCubeLeft(game);
        eventBus.listenAllDiseasesCured(game);
        eventBus.listenMax(game);
        eventBus.listenEradication(cureMarkerArea);
        cityInfector = new CityInfector();
        eventBus.listenInfectionCardDrawn(new Infector());
        eventBus.listenInfection(cityInfector);
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(outbreakCounter);
        eventBus.listenTreatment(new Treatment());
        eventBus.listenCureDiscovering(cureMarkerArea);
    }

}
