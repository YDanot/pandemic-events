package domain.player.cards;

import domain.infection.Disease;

import java.util.List;

public class SubHand {
    private final List<PlayerCard> cards;

    SubHand(List<PlayerCard> cards) {
        this.cards = cards;
    }

    public List<PlayerCard> cards() {
        return cards;
    }

    public int countDiseaseCard(Disease disease) {
        return (int) cards.stream().filter(c -> c.color().equals(PlayerCardColor.valueOf(disease.name()))).count();
    }
}
