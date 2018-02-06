package gadgets.shrewd.gui.panel.game.card.war;

import gadgets.shrewd.gui.panel.game.card.Deck;
import gadgets.shrewd.gui.panel.game.card.Outcome;
import gadgets.shrewd.gui.panel.game.card.PlayingCard;

import java.util.Optional;

/**
 * A simple card game that intimately uses the concept of a stack to manage collections
 * of playing cards between two players as they compete to obtain all the cards in a
 * standard playing card deck.
 */
public class War {

    private Deck deck;
    private Warrior challenger;
    private Warrior opponent;
    private boolean isGameInProgress;

    public War(Deck deck, Warrior challenger, Warrior opponent) {
        this.deck = deck;
        this.challenger = challenger;
        this.opponent = opponent;
    }

    public boolean isGameInProgress() {
        return this.isGameInProgress;
    }

    private void gameStarted() { this.isGameInProgress = true; }
    private void gameFinished() { this.isGameInProgress = false; }

    public Warrior getChallenger() {
        return this.challenger;
    }

    public Warrior getOpponent() {
        return this.opponent;
    }

    public void deal() {
        if (this.isGameInProgress())
            throw new IllegalStateException("A game is already in progress; players cannot be dealt cards.");

        if (this.deck.isEmpty())
            throw new IllegalStateException("The starter deck is empty; cards cannot be dealt to players with an empty deck.");

        while(this.deck.isNotEmpty()) {
            PlayingCard card = this.deck.pop()
                    .orElseThrow(IllegalStateException::new);

            if ((this.deck.size() % 2) == 0)
                this.opponent.putCardInHand(card);
            else
                this.challenger.putCardInHand(card);
        }
    }

    public void play(Aftermath outcome) {
        if (this.isGameInProgress())
            throw new IllegalStateException("A game is already in progress; another cannot be played yet.");

        //Lock the game to prevent resetting.
        this.gameStarted();

        //Retrieve the contenders.
        Warrior c = this.getChallenger();
        Warrior o = this.getOpponent();

        //Setup the outcome collector.
        outcome.log("%s challenges %s to War!", c, o);

        //Micro-deck needed for stacking played cards across turns.
        Deck turn = new Deck();
        while(outcome.hasNoVictor()) {
            if (turn.isNotEmpty() && !c.hasCards()) {
                //If this was a WAR-turn and the challenger has no more cards, opponent wins.
                outcome.exhausted(o, turn, c);
                continue;
            } else if (turn.isNotEmpty() && !o.hasCards()) {
                //If this was a WAR-turn and the opponent has no more cards, challenger wins.
                outcome.exhausted(c, turn, o);
                continue;
            }

            //At the start of the turn, each player draws a card.
            Optional<PlayingCard> optChallengerCard = c.getCardFromHand();
            Optional<PlayingCard> optOpponentCard = o.getCardFromHand();

            //Proactively store the cards in the turn deck to be allocated to the champion of the turn later.
            optChallengerCard.ifPresent(turn::push);
            optOpponentCard.ifPresent(turn::push);

            if (!optChallengerCard.isPresent()) {
                //If the challenger could not produce a card, opponent is the winner.
                outcome.exhausted(o, turn, c);
                continue;
            } else if(!optOpponentCard.isPresent()) {
                //If the opponent could not produce a card, challenger is the winner.
                outcome.exhausted(c, turn, o);
                continue;
            }

            //Unwrap the cards
            PlayingCard challengerCard = optChallengerCard.get();
            PlayingCard opponentCard = optOpponentCard.get();
            outcome.log("%s plays %s against %s's %s", c, challengerCard, o, opponentCard);

            int comparison = beats(challengerCard, opponentCard);
            switch (comparison) {
                case -1:
                    //Challenger lost; give all cards to opponent; count the turn.
                    outcome.turned(o, turn, c);
                    break;
                case 1:
                    //Opponent lost; give all cards to challenger; count the turn.
                    outcome.turned(c, turn, o);
                    break;
                case 0:
                    //WAR!  Equal card ranks were played; play again to determine turn winner.
                    //Each player must sacrifice one extra card and add it to the turn stack.
                    c.getCardFromHand().ifPresent(turn::push);
                    o.getCardFromHand().ifPresent(turn::push);
                    outcome.log("=====  WAR (%d cards up for grabs) =====", turn.size());
                    //Restart the loop
            }

        } //End turn while loop

        this.gameFinished();
        outcome.log("After %d turns, %s has beaten %s.", outcome.turns(),
                outcome.getWinner().orElse(Outcome.NO_ONE),
                outcome.getLoser().orElse(Outcome.ANYONE));
    }

    /**
     * In War, only the card Rank matters; the Suit is ignored.
     * @param c1 First card for comparison.
     * @param c2 Second card for comparison.
     * @return
     *      -1 => c1 loses to c2
     *      0  => c1 equals c2
     *      1  => c1 beats c2
     */
    int beats(PlayingCard c1, PlayingCard c2) {
        return Integer.compare(c1.getRank().rank(), c2.getRank().rank());
    }
}
