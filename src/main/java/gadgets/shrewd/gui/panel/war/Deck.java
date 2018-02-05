package gadgets.shrewd.gui.panel.war;

import gadgets.shrewd.core.Stackable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * A playing card deck represents a simple stack where cards are placed on top of and
 * sometimes popped off of during most card games.
 */
public class Deck implements Stackable<PlayingCard> {

    private List<PlayingCard> cards;

    public Deck() {
        this.cards = new ArrayList<>(PlayingCard.Suit.values().length * PlayingCard.Rank.values().length);
    }

    /**
     * Adds a PlayingCard to the top of the Deck.
     * @param card Instance of a PlayingCard
     * @throws IllegalArgumentException if the supplied PlayingCard instance is NULL.
     * @throws IllegalStateException if an equal PlayingCard is already in the Deck.
     */
    @Override
    public void push(PlayingCard card) {
        if (Objects.isNull(card))
            throw new IllegalArgumentException("Only an instance of a PlayingCard may be added to the Deck");

        if (this.cards.contains(card))
            throw new IllegalStateException("A Deck cannot contain duplicates of PlayingCards");

        this.cards.add(card);
    }

    /**
     * Removes a PlayingCard from the top of the Deck, if there is a PlayingCard in
     * the Deck at all.
     * @return Optional PlayingCard from top of Deck if available.
     */
    @Override
    public Optional<PlayingCard> pop() {
        return this.isEmpty() ?
                Optional.empty() :
                Optional.of(this.cards.remove(this.size() - 1));
    }

    /**
     * Returns the current Deck size, i.e. the count of PlayingCards presently in the Deck.
     * @return Current count of PlayingCards in Deck.
     */
    @Override
    public Integer size() {
        return this.cards.size();
    }

    /**
     * Checks to see if there is at least one PlayingCard in the Deck
     * @return True if there are no PlayingCards in the Deck; false otherwise.
     * @see Deck#isNotEmpty()
     */
    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    /**
     * Convenience method that inverts the isEmpty result for more expressive conditionals
     * @return True if the Deck contains at least one element.
     * @see Deck#isEmpty()
     */
    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    /**
     * Populates the Deck with the standard fifty-two (52) PlayingCards: one card
     * of each Rank for each Suite.
     * @throws IllegalStateException if the Deck is not empty when called.
     * @see PlayingCard.Suit
     * @see PlayingCard.Rank
     */
    public void populate() {
        if (this.isNotEmpty())
            throw new IllegalStateException("Cannot populate a Deck that already has playing cards in it");

        for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
            for (PlayingCard.Rank rank : PlayingCard.Rank.values()) {
                PlayingCard card = new PlayingCard(suit, rank);
                this.push(card);
            }
        }
    }

    /**
     * Shuffles the PlayingCards currently in the Deck.
     * A basic pseudo-random number generator (PRNG) should be sufficient for
     * proof-of-concept.
     */
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

    /**
     * Removes all the PlayingCards presently in the Deck and returns them.
     * @return Collection of PlayingCards popped from the Deck.
     */
    public Collection<PlayingCard> flush() {
        List<PlayingCard> cards = new ArrayList<>();
        while(this.isNotEmpty())
            cards.add(this.pop().orElseThrow(IllegalStateException::new));
        return cards;
    }
}
