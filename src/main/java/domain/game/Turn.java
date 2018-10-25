package domain.game;

public class Turn {

    private TurnId turnId;
    private Player player;
    private Gameplay gameplay;

    Turn(Player player) {
        this.turnId = new TurnId();
        this.player = player;
        this.gameplay = new Gameplay(this);
    }

    public TurnId id() {
        return turnId;
    }

    public Player player() {
        return player;
    }

    public Gameplay gameplay() {
        return gameplay;
    }
}
