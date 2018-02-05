package gadgets.shrewd.gui.panel.war;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WarTests {

    @Test
    public void go_to_war() {
        Deck starter = new Deck();
        starter.populate();
        starter.shuffle();

        Player challenger = new Player();
        Player opponent = new Player();
        War game = new War(starter, challenger, opponent);
        game.deal();
        Player winner = game.play();
        assertNotNull(winner);
    }
}
