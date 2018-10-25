package domain.game;

import domain.actions.role.dispatcher.Agreement;
import domain.role.Role;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Turn {

    private final Map<Role, Agreement> agreements;
    private TurnId turnId;
    private Player player;
    private Gameplay gameplay;

    Turn(Player player) {
        this.turnId = new TurnId();
        this.player = player;
        this.gameplay = new Gameplay(this);
        agreements = new HashMap<>();
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

    public void agreeToBeMovedByDispatcher(Role role) {
        agreements.put(role, Agreement.agree());
    }

    public void disagreeToBeMovedByDispatcher(Role role) {
        agreements.put(role, Agreement.disagree());
    }

    public Agreement agreementOf(Role role) {
        return Optional.ofNullable(agreements.get(role)).orElse(Agreement.disagree());
    }
}
