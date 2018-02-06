package gadgets.shrewd.gui.panel.game.card.war;

import gadgets.shrewd.gui.panel.game.card.Deck;
import gadgets.shrewd.gui.panel.game.card.Player;
import gadgets.shrewd.gui.panel.game.card.PlayingCard;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**
 * The Warrior is a specific kind player just for the game of War.
 * A warrior has a primary hand and a reserve pot of cards to play from.
 */
public class Warrior extends Player {

    private Deck hand;
    private Deck pot;

    public Warrior(String name) {
        super(name);
        this.hand = new Deck();
        this.pot = new Deck();
    }

    /**
     * Pushes all the supplied cards onto the reserve deck.
     * @param cards Collection of cards to push.
     */
    public void putCardInPot(Collection<PlayingCard> cards) {
        //Protects from null collection
        if (Objects.isNull(cards))
            cards = Collections.emptySet();

        cards.forEach(this.pot::push);
    }

    /**
     * Pushes the supplied card onto the hand deck.
     * Cards can only be played from the hand deck
     * @param card
     */
    void putCardInHand(PlayingCard card) {
        this.hand.push(card);
    }

    void refillHand() {
        this.pot.shuffle();
        while(this.pot.isNotEmpty()) {
            PlayingCard card = this.pot.pop()
                    .orElseThrow(IllegalStateException::new);
            this.putCardInHand(card);
        }
    }

    /**
     * Retrieves a card from the hand deck to play in a turn,
     * if one is available.
     * If the player has cards in their reserve pot of cards, the
     * hand is refilled from the reserve and play continues.
     * @return A playable card from the hand deck.
     */
    public Optional<PlayingCard> playCard() {
        Optional<PlayingCard> card = this.hand.pop();
        //If a card is available from the hand, play it.
        if (card.isPresent())
            return card;

        //If no cards were available, refill the hand and try again.
        this.refillHand();
        return this.hand.pop();
    }

    /**
     * Checks if the player has any cards at all.
     * @return True if the player has at least one card.
     * @see Warrior#hasNoCards()
     */
    public boolean hasCards() {
        return this.hand.isNotEmpty() || this.pot.isNotEmpty();
    }

    /**
     * Convenience method that inverts the hasCards result for
     * readability.
     * @return True has <u>no</u> cards remaining.
     */
    public boolean hasNoCards() {
        return !this.hasCards();
    }

    /**
     * Calculates the current number of cards owned by the player.
     * @return Integer count of cards across all decks.
     */
    int getCardCount() {
        return this.hand.size() + this.pot.size();
    }
}
