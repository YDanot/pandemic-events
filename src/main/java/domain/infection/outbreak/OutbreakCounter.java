package domain.infection.outbreak;


import domain.events.OutbreakEvent;

public class OutbreakCounter implements OutbreakListener{

    public int value;

    private void increase(){
        value++;
    }

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {
        increase();
    }
}
