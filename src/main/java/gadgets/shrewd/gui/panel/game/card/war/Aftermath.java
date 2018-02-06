package gadgets.shrewd.gui.panel.game.card.war;

import gadgets.shrewd.gui.panel.game.card.Deck;
import gadgets.shrewd.gui.panel.game.card.Outcome;

/**
 * Concrete implementation of a game outcome.
 * Convenience methods are available to centralize common meta-data activities related
 * to the specific game.
 */
public class Aftermath extends Outcome<Aftermath> {

    /**
     * Chainable method to assign the spoils of a turn.
     * @param champion Winning Warrior
     * @param spoils Turn deck to receive cards from.
     * @return Aftermath instance.
     */
    Aftermath spoils(Warrior champion, Deck spoils) {
        champion.putCardInPot(spoils.flush());
        return self();
    }

    /**
     * Applies the spoils of turn to the winner, increments the turn counter,
     * and logs several entries regarding the results of the turn.
     * @param champion Winner of the turn.
     * @param spoils Spoils for the winner to collect.
     * @param beaten Loser of the turn.
     * @return Aftermath instance.
     */
    Aftermath turned(Warrior champion, Deck spoils, Warrior beaten) {
        return self()
                .spoils(champion, spoils)
                .log("%s wins this round!", champion)
                .incrementTurns()
                .log("%s now has %d cards, %s has %d cards.",
                        champion, champion.getCardCount(), beaten, beaten.getCardCount())
                .log(" ");
    }

    /**
     * Ultimate victory condition for War: one of the players has exhausted their
     * supply of cards and can no longer play.
     * @param winner Winner of the game.
     * @param spoils Spoils for the winner to collect.
     * @param loser Loser of the game
     * @return Aftermath instance.
     */
    Aftermath exhausted(Warrior winner, Deck spoils, Warrior loser) {
        return self().log("%s has no more cards!", loser)
                .spoils(winner, spoils)
                .victory(winner, loser);
    }

    @Override
    protected Aftermath self() {
        return this;
    }
}
