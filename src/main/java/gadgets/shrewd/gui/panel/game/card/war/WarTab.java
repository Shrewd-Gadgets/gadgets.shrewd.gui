package gadgets.shrewd.gui.panel.game.card.war;

import gadgets.shrewd.gui.panel.TabbedPanel;
import gadgets.shrewd.gui.panel.TextFieldInputGroup;
import gadgets.shrewd.gui.panel.game.card.Deck;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WarTab extends TabbedPanel {

    private static final int DEFAULT_WIDTH = 25;

    public WarTab() {
        TextFieldInputGroup challenger = new TextFieldInputGroup.Builder()
                .text("Challenger:")
                .field("David")
                .columns(DEFAULT_WIDTH)
                .build();

        TextFieldInputGroup opponent = new TextFieldInputGroup.Builder()
                .text("Opponent:")
                .field("Goliath")
                .columns(DEFAULT_WIDTH)
                .build();


        JTextArea tale = new JTextArea();
        tale.setColumns(50);
        tale.setRows(10);
        tale.setEditable(false);

        JScrollPane scroll = new JScrollPane(tale);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Button button = new WarTab.Button(
                challenger.getComponent(),
                opponent.getComponent(),
                tale
        );

        this.containers = Arrays.asList(challenger, opponent, scroll, button);
    }

    class Button extends JPanel {
        Button(JTextField txtChallenger, JTextField txtOpponent, JTextArea txtTale) {
            JButton button = new JButton("PLAY");

            button.addActionListener(e -> {
                String cName = txtChallenger.getText();
                String oName = txtOpponent.getText();

                Warrior challenger = new Warrior(cName);
                Warrior opponent = new Warrior(oName);
                Aftermath outcome = new Aftermath();

                Deck starter = new Deck();
                starter.populate();
                starter.shuffle();

                War game = new War(starter, challenger, opponent);
                game.deal();
                game.play(outcome);

                List<String> logs = outcome.getLogs();
                for (String log : logs) {
                    txtTale.append(log.isEmpty() ? System.lineSeparator() : log);
                    txtTale.append(System.lineSeparator());
                }
            });

            this.add(button);
        }
    }

    @Override
    public String getTitle() {
        return "Cards: WAR";
    }

    @Override
    public ImageIcon getIcon() {
        return null;
    }

    @Override
    public Optional<String> getTip() {
        return Optional.of("Winner takes all in the game of War!");
    }
}
