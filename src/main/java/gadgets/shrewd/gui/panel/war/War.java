package gadgets.shrewd.gui.panel.war;

import java.util.Objects;
import java.util.Optional;

/**
 * A simple card game that intimately uses the concept of a stack to manage collections
 * of playing cards between two players as they compete to obtain all the cards in a
 * standard playing card deck.
 */
public class War {

    private Deck deck;
    private Player challenger;
    private Player opponent;
    private boolean isGameInProgress;

    public War(Deck deck, Player challenger, Player opponent) {
        this.deck = deck;
        this.challenger = challenger;
        this.opponent = opponent;
    }

    public boolean isGameInProgress() {
        return this.isGameInProgress;
    }

    private void gameStarted() { this.isGameInProgress = true; }
    private void gameFinished() { this.isGameInProgress = false; }

    public void deal() {
        if (this.isGameInProgress())
            throw new IllegalStateException("A game is already in progress; players cannot be dealt cards.");

        if (this.deck.isEmpty())
            throw new IllegalStateException("The starter deck is empty; cards cannot be dealt to players with an empty deck.");

        while(this.deck.isNotEmpty() && !this.isGameInProgress()) {
            PlayingCard card = this.deck.pop()
                    .orElseThrow(IllegalStateException::new);

            if ((this.deck.size() % 2) == 0)
                this.opponent.putCardInHand(card);
            else
                this.challenger.putCardInHand(card);
        }
    }

    public Player play() {
        if (this.isGameInProgress())
            throw new IllegalStateException("A game is already in progress; another cannot be played yet.");

        this.gameStarted();
        Player c = this.challenger;
        Player o = this.opponent;
        System.out.println(String.format("Challenger: %s\t\tOpponent: %s", c, o));

        Player winner = null;       //Placeholder for winning player.
        Deck turn = new Deck();     //Micro-deck needed for stacking played cards.
        int turnCount = 0;          //Simple counter to track played turns.
        while(Objects.isNull(winner)) {
            System.out.println(String.format(
                    "Current score: Challenger %d cards, Opponent %d cards",
                    c.getCardCount(), o.getCardCount()));

            if (turn.isNotEmpty() && !challenger.hasCards()) {
                //If this was a War turn and the challenger has no more cards, opponent wins.
                System.out.println("Challenger has no more cards!");
                o.putCardInPot(turn.flush());
                winner = o;
                continue;
            } else if (turn.isNotEmpty() && !opponent.hasCards()) {
                //If this was a War turn and the opponent has no more cards, challenger wins.
                System.out.println("Opponent has no more cards!");
                c.putCardInPot(turn.flush());
                winner = c;
                continue;
            }

            //At the start of the turn, each player draws a card.
            Optional<PlayingCard> optChallengerCard = c.getCardFromHand();
            optChallengerCard.ifPresent(turn::push);
            Optional<PlayingCard> optOpponentCard = o.getCardFromHand();
            optOpponentCard.ifPresent(turn::push);

            if (!optChallengerCard.isPresent()) {
                //If the challenger could not produce a card, opponent is the winner.
                System.out.println("Challenger has no more cards!");
                o.putCardInPot(turn.flush());
                winner = o;
                continue;
            } else if(!optOpponentCard.isPresent()) {
                //If the opponent could not produce a card, challenger is the winner.
                System.out.println("Opponent has no more cards!");
                c.putCardInPot(turn.flush());
                winner = c;
                continue;
            }

            PlayingCard cCard = optChallengerCard.get();
            PlayingCard oCard = optOpponentCard.get();
            System.out.println(String.format("%s\tvs.\t%s", cCard, oCard));
            int comparison = beats(cCard, oCard);
            switch (comparison) {
                case -1:
                    //Challenger lost; give all cards to opponent
                    o.putCardInPot(turn.flush());
                    System.out.println("Opponent wins!");
                    System.out.println(String.format(
                            "End of turn score: Challenger %d cards, Opponent %d cards\n",
                            c.getCardCount(), o.getCardCount()));
                    break;
                case 1:
                    //Opponent lost; give all cards to challenger
                    c.putCardInPot(turn.flush());
                    System.out.println("Challenger wins!");
                    System.out.println(String.format(
                            "End of turn score: Challenger %d cards, Opponent %d cards\n",
                            c.getCardCount(), o.getCardCount()));
                    break;
                case 0:
                    //War!  Play again to determine turn winner.
                    //Each player must draw one more card and push to turn stack.
                    System.out.println("=====  WAR  =====");
                    c.getCardFromHand().ifPresent(turn::push);
                    o.getCardFromHand().ifPresent(turn::push);
                    break;
            }
            turnCount++;
        } //End turn while loop

        this.gameFinished();
        System.out.println(String.format(
                "Final score: Challenger %d cards, Opponent %d cards after %d turns",
                c.getCardCount(), o.getCardCount(), turnCount));
        System.out.println(String.format("WINNER: %s!", winner).toUpperCase());
        return winner;
    }

    /**
     *
     * @param c1
     * @param c2
     * @return
     *      -1 => c1 loses to c2
     *      0  => c1 equals c2
     *      1  => c1 beats c2
     */
    int beats(PlayingCard c1, PlayingCard c2) {
        return Integer.compare(c1.getRank().rank(), c2.getRank().rank());
    }
}
