package gadgets.shrewd.gui.panel.game.card.war;

import gadgets.shrewd.gui.panel.game.card.Deck;
import gadgets.shrewd.gui.panel.game.card.Player;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WarTests {

    @Test
    public void go_to_war() {
        Deck starter = new Deck();
        starter.populate();
        starter.shuffle();

        Warrior challenger = new Warrior("David");
        Warrior opponent = new Warrior("Goliath");
        War game = new War(starter, challenger, opponent);
        game.deal();
        Player winner = game.play();
        assertNotNull(winner);
    }
}
