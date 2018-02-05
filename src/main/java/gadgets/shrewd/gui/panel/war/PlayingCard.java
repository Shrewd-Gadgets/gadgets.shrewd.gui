package gadgets.shrewd.gui.panel.war;

public class PlayingCard {

    public enum Suit { DIAMONDS, SPADES, HEARTS, CLUBS }

    public enum Rank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

        private int rank;
        Rank(int rank) { this.rank = rank; }
        public int rank() { return this.rank; }
    }

    private Suit suit;
    private Rank rank;

    public PlayingCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() { return this.suit; }
    public Rank getRank() { return this.rank; }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o) || !(o instanceof PlayingCard))
            return false;

        PlayingCard c = (PlayingCard)o;
        return this.getSuit().equals(c.getSuit()) && this.getRank().equals(c.getRank());
    }
}
