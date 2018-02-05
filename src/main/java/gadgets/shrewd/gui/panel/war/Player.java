package gadgets.shrewd.gui.panel.war;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public class Player {

    private Deck hand;
    private Deck pot;

    public Player() {
        this.hand = new Deck();
        this.pot = new Deck();
    }

    public Deck getHand() {
        return this.hand;
    }

    public Deck getPot() {
        return this.pot;
    }

    public void putCardInPot(PlayingCard card) {
        this.pot.push(card);
    }

    public void putCardInPot(Collection<PlayingCard> cards) {
        //Protects from null collection
        if (Objects.isNull(cards))
            cards = Collections.emptySet();

        cards.forEach(this::putCardInPot);
    }

    void putCardInHand(PlayingCard card) {
        this.hand.push(card);
    }

    public Optional<PlayingCard> getCardFromHand() {
        Optional<PlayingCard> card = this.hand.pop();
        //If a card is available from the hand, play it.
        if (card.isPresent())
            return card;

        //If no cards were available, refill the hand and try again.
        this.refillHand();
        return this.hand.pop();
    }

    public void refillHand() {
        this.pot.shuffle();
        while(this.pot.isNotEmpty()) {
            PlayingCard card = this.pot.pop()
                    .orElseThrow(IllegalStateException::new);
            this.putCardInHand(card);
        }
    }

    public boolean hasCards() {
        return this.hand.isNotEmpty() || this.pot.isNotEmpty();
    }

    public int getCardCount() {
        return this.hand.size() + this.pot.size();
    }
}
