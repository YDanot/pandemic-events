package domain;


import domain.events.OutbreakEvent;
import domain.OutbreakListener;

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
