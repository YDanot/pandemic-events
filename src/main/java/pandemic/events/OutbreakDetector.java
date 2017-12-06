package pandemic.events;


public class OutbreakDetector implements InfectionListener{

    @Override
    public void onInfection(InfectionEvent infectionEvent) {

        if (infectionEvent.originalLevel.maxReached()){
            World.get().eventBus.publish(new OutbreakEvent(infectionEvent.city));
        }
    }
}
