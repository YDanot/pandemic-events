package domain.game;

import domain.actions.Action;
import domain.infection.Disease;
import domain.player.cards.PlayerHand;
import domain.player.cards.SubHand;
import domain.role.Role;
import domain.treatment.cure.CureDiscoveringEvent;
import infra.World;

public class Player {

    private final Role role;

    public Player(Role role) {
        this.role = role;
    }

    public static Player as(Role role) {
        return new Player(role);
    }

    public Role role() {
        return role;
    }

    public void act(Action action) {
        action.accept(this);
    }

    public void cures(Disease disease, SubHand subHand) {
        PlayerHand playerHand = World.game.playerHands.handOf(this);
        if (noResearchStationBuiltOnLocation() || countDiseaseCardLowerThanFive(disease, subHand)) {
            throw new IllegalArgumentException("You cannot cure " + disease + " disease");
        }
        subHand.cards().forEach(playerHand::discard);
        World.eventBus.publish(new CureDiscoveringEvent(disease));
    }

    private boolean countDiseaseCardLowerThanFive(Disease disease, SubHand subHand) {
        return subHand.countDiseaseCard(disease) < 5;
    }

    private boolean noResearchStationBuiltOnLocation() {
        return !World.game.researchStations.builtOn(World.game.locations.locationsOf(role));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return role == player.role;
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
