package domain.infectioncards;

import domain.board.CitySteps;
import domain.infection.cards.InfectionCard;
import infra.World;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;


public class InfectionCardPilesTest {

    @Before
    public void setup() throws Throwable {
        World.create();
        new CitySteps().the_occident_sub_network();
        new InfectionCardsSteps().putAtTopOfDeck(InfectionCard.PARIS);
    }

    @Test
    public void discardPile_should_be_empty_when_game_starts() throws Exception {
        Assertions.assertThat(World.game.infectionCardsPiles.discardPile()).isEmpty();
    }

    @Test
    public void a_card_is_discard_after_being_drawn() throws Exception {
        InfectionCard draw = World.game.infectionCardsPiles.draw();
        Assertions.assertThat(World.game.infectionCardsPiles.discardPile()).containsOnly(draw);
    }

    @Test
    public void drawPile_should_be_full_when_game_starts() throws Exception {
        Assertions.assertThat(World.game.infectionCardsPiles.drawPile()).hasSize(48);
    }

}