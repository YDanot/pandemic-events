package domain.game.start;

import domain.game.Player;
import domain.game.Players;
import domain.game.TurnId;
import domain.role.Role;
import infra.World;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DealerTest {

    @Test
    public void a_2_players_game_should_start_with_4_cards_in_hand() throws Exception {
        World.create();
        Player medic = Player.as(Role.MEDIC);
        Player scientist = Player.as(Role.SCIENTIST);
        new Dealer().deal(Players.of(medic, scientist), new TurnId());
        Assertions.assertThat(World.game.playerHands.handOf(medic).get()).hasSize(4);
        Assertions.assertThat(World.game.playerHands.handOf(scientist).get()).hasSize(4);
    }

    @Test
    public void a_3_players_game_should_start_with_3_cards_in_hand() throws Exception {
        World.create();
        Player medic = Player.as(Role.MEDIC);
        Player scientist = Player.as(Role.SCIENTIST);
        Player dispatcher = Player.as(Role.DISPATCHER);
        new Dealer().deal(Players.of(medic, scientist, dispatcher), new TurnId());
        Assertions.assertThat(World.game.playerHands.handOf(medic).get()).hasSize(3);
        Assertions.assertThat(World.game.playerHands.handOf(scientist).get()).hasSize(3);
        Assertions.assertThat(World.game.playerHands.handOf(dispatcher).get()).hasSize(3);
    }

    @Test
    public void a_4_players_game_should_start_with_2_cards_in_hand() throws Exception {
        World.create();
        Player medic = Player.as(Role.MEDIC);
        Player scientist = Player.as(Role.SCIENTIST);
        Player dispatcher = Player.as(Role.DISPATCHER);
        Player researcher = Player.as(Role.RESEARCHER);
        new Dealer().deal(Players.of(medic, scientist, dispatcher, researcher), new TurnId());
        Assertions.assertThat(World.game.playerHands.handOf(medic).get()).hasSize(2);
        Assertions.assertThat(World.game.playerHands.handOf(scientist).get()).hasSize(2);
        Assertions.assertThat(World.game.playerHands.handOf(dispatcher).get()).hasSize(2);
        Assertions.assertThat(World.game.playerHands.handOf(researcher).get()).hasSize(2);
    }

}