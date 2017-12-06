package pandemic.events;


public class Disease implements InfectionListener{

    @Override
    public void onInfection(InfectionEvent infectionEvent) {
        infectionEvent.city.infect();
    }
}
