package pandemic;

public enum City {
    PARIS,
    ESSEN,
    ALGER,
    MADRID,
    MILAN,
    NEW_YORK,
    LONDON;

    public InfectionLevel level = InfectionLevel.from(0);

    public void infect(){
        level = level.increase();
    }

    public static void resetAll() {
        for (City city : values()) {
            city.reset();
        }
    }

    private void reset() {
        level = InfectionLevel.from(0);
    }
}
