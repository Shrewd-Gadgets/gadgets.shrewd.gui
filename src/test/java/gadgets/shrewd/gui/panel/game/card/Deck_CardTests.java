package gadgets.shrewd.gui.panel.game.card;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class Deck_CardTests {

    public static class Populate {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        private Deck mDeck;

        @Before
        public void setup() {
            mDeck = mock(Deck.class);
            doCallRealMethod().when(mDeck).populate();
        }

        @Test
        public void throws_when_deck_is_not_empty() {
            thrown.expect(IllegalStateException.class);
            thrown.expectMessage("Cannot populate a Deck that already has playing cards in it");

            when(mDeck.isNotEmpty()).thenReturn(Boolean.TRUE);
            mDeck.populate();
        }

        @Test
        public void verify_population_of_deck() {
            List<PlayingCard> expected = Arrays.asList(
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.ACE),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.TWO),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.THREE),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.FOUR),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.FIVE),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.SIX),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.SEVEN),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.EIGHT),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.NINE),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.TEN),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.JACK),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.QUEEN),
                    new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.KING),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.ACE),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.TWO),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.THREE),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.FOUR),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.FIVE),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.SIX),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.SEVEN),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.EIGHT),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.NINE),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.TEN),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.JACK),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.QUEEN),
                    new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.KING),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.ACE),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TWO),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.THREE),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.FOUR),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.FIVE),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.JACK),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.QUEEN),
                    new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.ACE),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.TWO),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.THREE),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.FOUR),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.FIVE),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.SIX),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.SEVEN),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.EIGHT),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.NINE),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.TEN),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.JACK),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.QUEEN),
                    new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.KING)
            );

            Deck deck = new Deck();
            deck.populate();
            assertEquals(expected, deck.getCards());
        }
    }

    public static class Shuffle {

        @Test
        public void swap_card_locations_with_mock_prng() {
            PlayingCard d = new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.ACE);
            PlayingCard s = new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.ACE);
            PlayingCard h = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.ACE);
            PlayingCard c = new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.ACE);

            List<PlayingCard> cards = Arrays.asList(d, s, h, c);

            Random mRandom = mock(Random.class);
            when(mRandom.nextInt(4))
                    .thenReturn(1)  // 's, d, h, c'
                    .thenReturn(3)  // 's, c, h, d'
                    .thenReturn(0)  // 'h, c, s, d'
                    .thenReturn(1); // 'h, d, s, c'


            Deck mDeck = mock(Deck.class, CALLS_REAL_METHODS);
            when(mDeck.getCards()).thenReturn(cards);

            mDeck.shuffle(mRandom);

            assertEquals(h, cards.get(0));  //HEARTS
            assertEquals(d, cards.get(1));  //DIAMONDS
            assertEquals(s, cards.get(2));  //SPADES
            assertEquals(c, cards.get(3));  //CLUBS
        }
    }

    public static class Flush {

        @SuppressWarnings("unchecked")  //This is acceptable only for testing!
        @Test
        public void swap_card_locations_with_mock_prng() {
            PlayingCard d = new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.ACE);
            PlayingCard s = new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.ACE);
            PlayingCard h = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.ACE);
            PlayingCard c = new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.ACE);

            Deck mDeck = mock(Deck.class, RETURNS_SMART_NULLS);
            when(mDeck.flush()).thenCallRealMethod();
            when(mDeck.isNotEmpty()).thenReturn(
                    Boolean.TRUE,
                    Boolean.TRUE,
                    Boolean.TRUE,
                    Boolean.TRUE,
                    Boolean.FALSE);
            when(mDeck.pop()).thenReturn(
                    Optional.of(d),
                    Optional.of(s),
                    Optional.of(h),
                    Optional.of(c),
                    Optional.empty());

            List<PlayingCard> expected = Arrays.asList(d, s, h, c);
            Collection<PlayingCard> results = mDeck.flush();

            assertEquals(expected, results);

            verify(mDeck, times(5)).isNotEmpty();
            verify(mDeck, times(4)).pop();
        }
    }
}
