package domain.player.cards;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PlayerCardsPilesTest {

    @Test
    public void addEpidemicCardsToDrawPile() throws Exception {

        PlayerCardsPiles playerCardsPiles = new PlayerCardsPiles();
        playerCardsPiles.addEpidemicCardsToDrawPile(6);

        Assertions.assertThat(playerCardsPiles.drawPile().size()).isEqualTo(54);
        Assertions.assertThat(playerCardsPiles.drawPile().subList(0, 9)).contains(PlayerCard.EPIDEMIC);
        Assertions.assertThat(playerCardsPiles.drawPile().subList(9, 18)).contains(PlayerCard.EPIDEMIC);
        Assertions.assertThat(playerCardsPiles.drawPile().subList(18, 27)).contains(PlayerCard.EPIDEMIC);
        Assertions.assertThat(playerCardsPiles.drawPile().subList(27, 36)).contains(PlayerCard.EPIDEMIC);
        Assertions.assertThat(playerCardsPiles.drawPile().subList(36, 45)).contains(PlayerCard.EPIDEMIC);
        Assertions.assertThat(playerCardsPiles.drawPile().subList(45, 54)).contains(PlayerCard.EPIDEMIC);
    }

}