package gadgets.shrewd.gui.panel.war;

import gadgets.shrewd.core.Stackable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class Deck implements Stackable<PlayingCard> {

    private List<PlayingCard> cards;

    public Deck() {
        this.cards = new ArrayList<>(PlayingCard.Suit.values().length * PlayingCard.Rank.values().length);
    }

    List<PlayingCard> getCards() { return this.cards; }

    @Override
    public void push(PlayingCard card) {
        if (Objects.isNull(card))
            throw new IllegalArgumentException("Only an instance of a PlayingCard may be added to the Deck");

        if (this.cards.contains(card))
            throw new IllegalArgumentException("A Deck cannot contain duplicates of PlayingCards");
        this.cards.add(card);
    }

    @Override
    public Optional<PlayingCard> pop() {
        return this.isEmpty() ?
                Optional.empty() :
                Optional.of(this.cards.remove(this.size() - 1));
    }

    @Override
    public Integer size() {
        return this.cards.size();
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public void populate() {
        if (this.isNotEmpty())
            throw new IllegalStateException("Cannot populate a Deck that already has Playing Cards in it");

        for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
            for (PlayingCard.Rank rank : PlayingCard.Rank.values()) {
                PlayingCard card = new PlayingCard(suit, rank);
                this.push(card);
            }
        }
    }

    public void shuffle() {
        Random rand = new Random();
        this.shuffle(rand);
    }

    /**
     * Shuffles the Deck using the supplied pseudo-random number generator (PRNG).
     * Package-protected to allow the injection of a seeded PRNG during testing.
     * @param rand Random number generator.
     */
    void shuffle(Random rand) {
        int size = this.size();
        PlayingCard swap;
        for(int i = 0; i < size; i++) {
            swap = this.cards.get(i);

            int s = rand.nextInt(size);
            this.cards.set(i, this.cards.get(s));
            this.cards.set(s, swap);
        }
    }
}
