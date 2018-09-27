package domain.infection.outbreak;

import infra.World;

public class OutbreakCounter implements OutbreakListener {

    int value;

    private void increase(){
        value++;
        if (value == 8){
            World.eventBus.publish(new MaxOutbreakNumberReachedEvent());
        }
    }

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {
        increase();
    }
}
