package pandemic.events;


import pandemic.City;
import pandemic.Network;
import pandemic.OutbreakCounter;

public class World {

    private static World me;

    public static World get(){
        if (null == me){
            me = new World();
        }

        return me;
    }

    public final EventBus eventBus;

    public final Network network;

    public final OutbreakCounter outbreakCounter;

    public World() {
        eventBus = new EventBus();
        network = new Network();
        outbreakCounter = new OutbreakCounter();
        eventBus.listenInfection(new Disease());
        eventBus.listenInfection(new OutbreakDetector());
        eventBus.listenOutbreak(new OutbreakPropagator());
        eventBus.listenOutbreak(outbreakCounter);
        City.resetAll();
    }

    public static void reset() {
        me = new World();
    }
}
