package gadgets.shrewd.gui.panel.game.card;

/**
 * Extremely thin class representing a generic player of games.
 */
public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    /**
     * Retrieves the players' name
     * @return "Kevin Flynn"
     */
    public String getName() { return this.name; }

    /**
     * Sets the player's name
     * @param name "Kevin Flynn"
     */
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() { return this.getName(); }
}
