package gadgets.shrewd.gui.panel.game.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A template game results class that uses reflexive templating to make
 * inheritable, chainable method calls accessible to other implementations.
 * @param <OUT>
 */
public abstract class Outcome<OUT extends Outcome> {

    private Player winner;
    private Player loser;
    private int turns = 0;
    private List<String> logs = new ArrayList<>();

    public static final Player NO_ONE = new Player("No one");
    public static final Player ANYONE = new Player("anyone");

    /**
     * Any sub-class that implements this method can participate in the chained
     * method calls without losing class specificity.
     * @return Outcome instance.
     */
    protected abstract OUT self();

    /**
     * Retrieves the current number of completed turns.
     * The onus is on game creators to define what a "turn" consists of and when it has
     * been "completed";
     * @return Current completed turns count
     */
    public int getTurns() { return this.turns; }

    /**
     * Chainable turn counter incrementer.
     * @return Outcome instance.
     */
    public OUT incrementTurns() {
        this.turns++;
        return self();
    }

    /**
     * Chainable victory declaration where the winner and the loser are decided.
     * @param winner Winning player.
     * @param loser Losing player.
     * @return Outcome instance.
     */
    public OUT victory(Player winner, Player loser) {
        this.winner = winner;
        this.loser = loser;
        return self();
    }

    /**
     * Retrieves the winner of the game, if one is available.
     * @return Winning player
     */
    public Optional<Player> getWinner() {
        return Optional.ofNullable(this.winner);
    }

    /**
     * Retrieves the loser of the game, if one is available.
     * @return Losing player
     */
    public Optional<Player> getLoser() {
        return Optional.ofNullable(this.loser);
    }

    /**
     * Checks to see if the game has a winner
     * @return True if a winner has been assigned; false otherwise.
     */
    public boolean hasVictor() {
        return this.getWinner().isPresent();
    }

    /**
     * Convenience method for inverting the hasVictor interrogative.
     * @return True if a winner has <u>not</u> been assigned; false otherwise.
     */
    public boolean hasNoVictor() {
        return !this.hasVictor();
    }

    /**
     * Adds a log entry to the collection of messages.
     * Empty entries will be added, but NULL entries will not.
     * @param entry String log entry with
     * @param args Collection of String formatting arguments for interpolation.
     * @return Outcome instance
     */
    public OUT log(String entry, Object... args) {
        if (!Objects.isNull(entry))
            this.logs.add(String.format(entry, args));
        return self();
    }

    /**
     * Retrieves the collection of log entries.
     * @return Ordered collection of log entries.
     */
    public List<String> getLogs() {
        return this.logs;
    }
}
