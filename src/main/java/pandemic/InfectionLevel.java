package pandemic;

public class InfectionLevel {

    private static final int MAX = 3;

    private final int value;

    public InfectionLevel(int value) {
        this.value = value;
    }

    public static InfectionLevel from(int level) {
        return new InfectionLevel(level);
    }

    public InfectionLevel increase() {
        if (value < MAX)
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

    public boolean maxReached() {
        return MAX == value;
    }
}
