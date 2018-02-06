package gadgets.shrewd.gui.panel.game.card;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayingCardTest {

    @Test
    public void sanity_check_numerical_rank_values() {
        //Simple typo protection
        assertEquals("Ace has been altered",
                1, PlayingCard.Rank.ACE.rank());
        assertEquals("Two has been altered",
                2, PlayingCard.Rank.TWO.rank());
        assertEquals("Three has been altered",
                3, PlayingCard.Rank.THREE.rank());
        assertEquals("Four has been altered",
                4, PlayingCard.Rank.FOUR.rank());
        assertEquals("Five has been altered",
                5, PlayingCard.Rank.FIVE.rank());
        assertEquals("Six has been altered",
                6, PlayingCard.Rank.SIX.rank());
        assertEquals("Seven has been altered",
                7, PlayingCard.Rank.SEVEN.rank());
        assertEquals("Eight has been altered",
                8, PlayingCard.Rank.EIGHT.rank());
        assertEquals("Nine has been altered",
                9, PlayingCard.Rank.NINE.rank());
        assertEquals("Ten has been altered",
                10, PlayingCard.Rank.TEN.rank());
        assertEquals("Jack has been altered",
                11, PlayingCard.Rank.JACK.rank());
        assertEquals("Queen has been altered",
                12, PlayingCard.Rank.QUEEN.rank());
        assertEquals("King has been altered",
                13, PlayingCard.Rank.KING.rank());
    }

    @Test
    public void sanity_check_to_string_representation() {
        //Small sampling just to check simple formatting
        assertEquals("ACE of SPADES",
                new PlayingCard(PlayingCard.Suit.SPADES, PlayingCard.Rank.ACE).toString());
        assertEquals("SEVEN of DIAMONDS",
                new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.SEVEN).toString());
        assertEquals("KING of HEARTS",
                new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING).toString());
        assertEquals("THREE of CLUBS",
                new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.THREE).toString());
    }

    @Test
    public void custom_equality_between_playing_cards() {
        PlayingCard card = new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.JACK);

        assertFalse("NULL should not be equivalent", card.equals(null));
        assertFalse("Non-instance should not be equivalent", card.equals(new Object()));

        PlayingCard absent = null;
        assertFalse("Absent should not be equivalent", card.equals(absent));

        PlayingCard diff = new PlayingCard(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.FOUR);
        assertFalse("Different should not be equivalent", card.equals(diff));

        PlayingCard dupe = new PlayingCard(PlayingCard.Suit.CLUBS, PlayingCard.Rank.JACK);
        assertTrue("Duplicate should be equivalent", card.equals(dupe));

        assertTrue("Identical should be equivalent", card.equals(card));
    }
}
