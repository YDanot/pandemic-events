package domain.infection;

public class InfectionLevel {

    private static final int OUTBREAK_LEVEL = 3;

    private final Disease disease;

    private final int value;

    private InfectionLevel(Disease disease, int value) {
        this.disease = disease;
        this.value = value;
    }


    public static InfectionLevel from(Disease disease, int level) {
        return new InfectionLevel(disease, level);
    }

    InfectionLevel increase() {
        if (value < OUTBREAK_LEVEL)
            return from(disease, value+1);

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
