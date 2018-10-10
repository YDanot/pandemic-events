package domain.actions;

public class ActionImpossible extends RuntimeException {
    public ActionImpossible(String message) {
        super(message);
    }

    public ActionImpossible() {
        super();
    }
}
