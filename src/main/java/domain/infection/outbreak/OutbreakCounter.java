package domain.infection.outbreak;

public class OutbreakCounter implements OutbreakListener {

    int value;

    private void increase(){
        value++;
    }

    @Override
    public void onOutbreak(OutbreakEvent outbreakEvent) {
        increase();
    }
}
