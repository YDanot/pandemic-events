package infra;


import domain.CityInfector;
import domain.InfectionListener;
import domain.Network;
import domain.OutbreakCounter;
import domain.OutbreakDetector;
import domain.OutbreakPropagator;
import domain.events.InfectionEvent;
import domain.events.OutbreakEvent;
import infra.EventBus;

public class World {

    public static EventBus eventBus;

    private static Network network;

    public static OutbreakCounter outbreakCounter;

    static {
        create();
    }

    private static void create() {
        eventBus = new EventBus();
        network = new Network();
        outbreakCounter = new OutbreakCounter();
        eventBus.listenInfection(new CityInfector());
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(outbreakCounter);
    }

    public static Network getNetwork() {
        return network;
    }

    public static void reset() {
        create();
    }

    public static void publish(InfectionEvent infectionEvent){
        eventBus.publish(infectionEvent);
    }

    public static void publish(OutbreakEvent outbreakEvent){
        eventBus.publish(outbreakEvent);
    }

    public static OutbreakCounter getOutbreakCounter() {
        return outbreakCounter;
    }
}
