package domain.infection.rate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import static domain.infection.rate.InfectionRateTrack.InfectionRate.*;

public class InfectionRateTrack {

    private final ImmutableList<InfectionRate> rates = new Builder<InfectionRate>().add(TWO, TWO, TWO, THREE, THREE, FOUR, FOUR).build();

    private int currentRate = 0;

    int current() {
        return rates.get(currentRate).value;
    }

    public int moveUp() {
        currentRate++;
        return current();
    }

    enum InfectionRate {
        TWO(2),
        THREE(3),
        FOUR(4);

        private int value;

        InfectionRate(int value) {

            this.value = value;
        }
    }
}
