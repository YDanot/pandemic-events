package domain.actions.role.dispatcher;

public class Agreement {

    private final boolean agreed;

    private Agreement(boolean agreed) {
        this.agreed = agreed;
    }

    public static Agreement agree() {
        return new Agreement(true);
    }

    public static Agreement disagree() {
        return new Agreement(false);
    }

    boolean agreed() {
        return agreed;
    }
}
