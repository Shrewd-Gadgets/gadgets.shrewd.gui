package gadgets.shrewd.gui.panel.game.card;

/**
 * A basic representation of a standard range of playing cards, suitable for most card
 * games.
 */
public class PlayingCard {

    /**
     * Card suits; defines half the tuple comprising a single card.
     * @see Rank
     */
    public enum Suit { DIAMONDS, SPADES, HEARTS, CLUBS }

    /**
     * Card rank; defines half the tuple comprising a single card.
     */
    public enum Rank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

        private int rank;
        Rank(int rank) { this.rank = rank; }

        /**
         * The inherent value of a card represented numerically for comparison purposes.
         * For example, a "JACK" is "greater than" an "EIGHT" because <b>11 > 8</b>
         * @return Numerical rank value.
         */
        public int rank() { return this.rank; }
    }

    private final Suit suit;
    private final Rank rank;

    /**
     * A playing card instance has an immutable Suit and Rank that comprises it's individuality,
     * but not it's uniqueness.
     * For example, two instances of a PlayingCard, each having the Suit DIAMONDS and the
     * Rank SEVEN, are considered equivalent when compared.
     * @param suit Immutable Suit assignment.
     * @param rank Immutable Rank assignment.
     */
    public PlayingCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Retrieves the cards Suit
     * @return Current Suit value.
     */
    public Suit getSuit() { return this.suit; }
    public Rank getRank() { return this.rank; }

    /**
     * Common English format spoken "RANK of SUIT", e.g. "ACE of SPADES"
     * @return "ACE of SPADES"
     */
    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }

    /**
     * Two PlayingCards are <u>not</u> considered equal if:
     * <ul>
     *     <li>They are the not same instance of the object; OR</li>
     *     <li>One is not an instance of a PlayingCard; OR</li>
     *     <li>The Suits AND Ranks are not the same</li>
     * </ul>
     * @param o Object to compare against this PlayingCard.
     * @return True if they equivalent; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (super.equals(o))
            return true;

        if (!(o instanceof PlayingCard))
            return false;

        PlayingCard c = (PlayingCard)o;
        return (this.suit.equals(c.getSuit()) && this.rank.equals(c.getRank()));
    }
}
