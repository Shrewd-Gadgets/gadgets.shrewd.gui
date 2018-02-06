package gadgets.shrewd.gui.panel.game.card.war;

import gadgets.shrewd.gui.panel.game.card.Deck;
import org.junit.Test;

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

        Aftermath outcome = new Aftermath();
        game.play(outcome);
        System.out.print(String.join(System.lineSeparator(), outcome.getLogs()));
    }
}
