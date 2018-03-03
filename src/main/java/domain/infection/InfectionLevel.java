
package domain.infection;

public class InfectionLevel {

    public static final InfectionLevel ZERO = from(0);

    private static final int OUTBREAK_LEVEL = 3;

    private final int value;

    private InfectionLevel(int value) {
        this.value = value;
    }

    public static InfectionLevel from(int level) {
        return new InfectionLevel(level);
    }

    public InfectionLevel increase() {
        return from(value + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        InfectionLevel that = (InfectionLevel) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    public boolean outbreakLevelReached() {
        return OUTBREAK_LEVEL == value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
