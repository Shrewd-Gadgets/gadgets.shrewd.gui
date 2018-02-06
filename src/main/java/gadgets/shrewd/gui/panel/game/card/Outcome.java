package gadgets.shrewd.gui.panel.game.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Outcome<OUT extends Outcome> {

    private Player winner;
    private Player loser;
    private int turns = 0;
    private List<String> tale = new ArrayList<>();

    public static final Player NO_ONE = new Player("No one");
    public static final Player ANYONE = new Player("anyone");

    protected abstract OUT self();

    public int turns() { return this.turns; }

    public OUT countTurn() {
        this.turns++;
        return self();
    }

    public OUT victory(Player winner, Player loser) {
        this.winner = winner;
        this.loser = loser;
        return self();
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(this.winner);
    }

    public Optional<Player> getLoser() {
        return Optional.ofNullable(this.loser);
    }

    public boolean hasVictor() {
        return !Objects.isNull(this.winner);
    }

    public boolean hasNoVictor() {
        return !this.hasVictor();
    }

    public OUT log(String tale, Object... args) {
        if (!Objects.isNull(tale) && !tale.trim().isEmpty())
            this.tale.add(String.format(tale, args));
        return self();
    }

    public List<String> getTale() {
        return this.tale;
    }
}
