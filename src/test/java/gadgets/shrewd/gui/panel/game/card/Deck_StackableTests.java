package gadgets.shrewd.gui.panel.game.card;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class Deck_StackableTests {

    private static final PlayingCard CARD =
            new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING);

    public static class Push {
        @Rule
        public ExpectedException thrown = ExpectedException.none();

        private static Deck DECK;

        @Before
        public void setup() {
            DECK = new Deck();
        }

        @Test
        public void throws_with_null_card() {
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage("Only an instance of a PlayingCard may be added to the Deck");

            DECK.push(null);
        }

        @Test
        public void throws_with_duplicate_card() {
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage("A Deck cannot contain duplicates of PlayingCards");

            DECK.push(CARD);
            DECK.push(CARD);
        }

        @Test
        public void card_added_to_stack() {
            DECK.push(CARD);

            List<PlayingCard> cards = DECK.getCards();
            assertNotNull("Deck should have cards.", cards);
            assertEquals(1, cards.size());
            assertEquals(CARD, cards.get(0));
        }
    }

    public static class Pop {

        private Deck mDeck;

        @Before
        public void setup() {
            mDeck = mock(Deck.class, RETURNS_SMART_NULLS);
            when(mDeck.pop()).thenCallRealMethod();
        }

        @Test
        public void return_empty_optional_when_deck_is_empty() {
            when(mDeck.getCards()).thenReturn(Collections.emptyList());

            Optional<PlayingCard> result = mDeck.pop();
            assertEquals(Optional.empty(), result);
        }

        @Test
        public void return_optional_wrapped_card_from_collection() {
            List<PlayingCard> cards = new ArrayList<>();
            cards.add(CARD);
            when(mDeck.getCards()).thenReturn(cards);

            Optional<PlayingCard> result = mDeck.pop();
            assertEquals(Optional.of(CARD), result);
        }
    }

    public static class Size {

        private Deck mDeck;

        @Before
        public void setup() {
            mDeck = mock(Deck.class, RETURNS_SMART_NULLS);
            when(mDeck.size()).thenCallRealMethod();
        }

        @Test
        public void return_size_from_underlying_collection() {
            when(mDeck.getCards())
                    .thenReturn(Collections.emptyList())
                    .thenReturn(Collections.singletonList(mock(PlayingCard.class)));

            assertEquals((Integer)0, mDeck.size());
            assertEquals((Integer)1, mDeck.size());
        }
    }

    public static class Empty {

        private Deck mDeck;

        @Before
        public void setup() {
            mDeck = mock(Deck.class, RETURNS_SMART_NULLS);
            when(mDeck.isEmpty()).thenCallRealMethod();
            when(mDeck.isNotEmpty()).thenCallRealMethod();
        }

        @Test
        public void return_empty_state_from_underlying_collection() {
            when(mDeck.getCards())
                    .thenReturn(Collections.emptyList())
                    .thenReturn(Collections.emptyList())
                    .thenReturn(Collections.singletonList(mock(PlayingCard.class)))
                    .thenReturn(Collections.singletonList(mock(PlayingCard.class)));

            assertTrue(mDeck.isEmpty());
            assertFalse(mDeck.isNotEmpty());
            assertFalse(mDeck.isEmpty());
            assertTrue(mDeck.isNotEmpty());
        }
    }

}
