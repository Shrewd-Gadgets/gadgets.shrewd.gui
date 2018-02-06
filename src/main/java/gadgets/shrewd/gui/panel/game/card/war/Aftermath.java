package gadgets.shrewd.gui.panel.game.card.war;

import gadgets.shrewd.gui.panel.game.card.Deck;
import gadgets.shrewd.gui.panel.game.card.Outcome;

public class Aftermath extends Outcome<Aftermath> {

    public Aftermath spoils(Warrior warrior, Deck spoils) {
        warrior.putCardInPot(spoils.flush());
        return self();
    }

    public Aftermath turned(Warrior champion, Deck spoils, Warrior beaten) {
        return self()
                .spoils(champion, spoils)
                .log("%s wins this round!", champion)
                .countTurn()
                .log("%s now has %d cards, %s has %d cards.",
                        champion, champion.getCardCount(), beaten, beaten.getCardCount())
                .log(" ");
    }

    public Aftermath exhausted(Warrior winner, Deck spoils, Warrior loser) {
        return self().log("%s has no more cards!", loser)
                .spoils(winner, spoils)
                .victory(winner, loser);
    }

    @Override
    protected Aftermath self() {
        return this;
    }
}
