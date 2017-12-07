package domain.infection;

public class InfectionLevel {

    private static final int OUTBREAK_LEVEL = 3;

    private final int value;

    private InfectionLevel(int value) {
        this.value = value;
    }

    public static InfectionLevel from(int level) {
        return new InfectionLevel(level);
    }

    InfectionLevel increase() {
        if (value < OUTBREAK_LEVEL)
            return from(value+1);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfectionLevel that = (InfectionLevel) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    boolean outbreakLevelReached() {
        return OUTBREAK_LEVEL == value;
    }

    @Override public String toString() {
        return String.valueOf(value);
    }
}
