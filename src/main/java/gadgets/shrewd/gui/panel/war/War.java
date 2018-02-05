package gadgets.shrewd.gui.panel.war;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class War {

    private Deck deck;
    private Player challenger;
    private Player opponent;

    public War(Deck deck, Player challenger, Player opponent) {
        this.deck = deck;
        this.challenger = challenger;
        this.opponent = opponent;
    }

    public void deal() {
        while(this.deck.isNotEmpty()) {
            PlayingCard card = this.deck.pop()
                    .orElseThrow(IllegalStateException::new);

            if ((this.deck.size() % 2) == 0)
                this.opponent.putCardInHand(card);
            else
                this.challenger.putCardInHand(card);
        }
    }

    public Player play() {
        Player c = this.challenger;
        Player o = this.opponent;

        //The game runs until one player has no cards left.
        Player winner = null;
        Turn turn = new Turn();
        System.out.println(String.format("Challenger: %s\t\tOpponent: %s", c, o));
        int turnCount = 0;
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


        System.out.println(String.format(
                "Final score: Challenger %d cards, Opponent %d cards after %d turns",
                c.getCardCount(), o.getCardCount(), turnCount));
        System.out.println(String.format("Winner: %s", winner));
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

    class Turn extends Deck {

        public List<PlayingCard> flush() {
            List<PlayingCard> cards = new ArrayList<>();
            while(this.isNotEmpty())
                cards.add(this.pop().orElseThrow(IllegalStateException::new));
            return cards;
        }
    }
}
