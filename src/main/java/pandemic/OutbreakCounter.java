package pandemic;


import pandemic.events.OutbreakEvent;
import pandemic.events.OutbreakListener;

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
