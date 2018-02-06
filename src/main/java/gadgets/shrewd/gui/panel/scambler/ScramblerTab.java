package gadgets.shrewd.gui.panel.scambler;

import gadgets.shrewd.core.util.Words;
import gadgets.shrewd.gui.panel.TabbedPanel;
import gadgets.shrewd.gui.panel.TextFieldInputGroup;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class ScramblerTab extends TabbedPanel {

    private static final int DEFAULT_WIDTH = 300;

    public ScramblerTab() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        TextFieldInputGroup sentence = new TextFieldInputGroup.Builder()
                .text("Sentence:")
                .field("Here is a default sentence.")
                .columns(DEFAULT_WIDTH)
                .build();
        TextFieldInputGroup reversed = new TextFieldInputGroup.Builder()
                .text("Reversed:")
                .columns(DEFAULT_WIDTH)
                .build();
        Container button = new ScramblerTab.Button(
                sentence.getComponent(),
                reversed.getComponent());

        this.containers = Arrays.asList(sentence, reversed, button);
    }

    /**
     * Internal button instance that interacts with other defined elements of the pane.
     *
     * This button retrieves the text in the source text field box, and places result text
     * in the results text field.
     */
    class Button extends JPanel {

        Button(JTextField source, JTextField result) {
            JButton button = new JButton("SCRAMBLE");

            button.addActionListener(e -> {
                String value = source.getText();
                String scrambled = Words.reverse(value);

                System.out.println(String.format("Current value of sentence: %s", value));
                System.out.println(String.format("Reversed value of sentence: %s", scrambled));

                result.setText(scrambled);
                result.repaint();
            });

            this.add(button);
        }
    }

    @Override
    public String getTitle() {
        return "Scrambler";
    }

    @Override
    public Optional<ImageIcon> getIcon() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getTip() {
        return Optional.of("Reverse your sentences to confuse your enemies!");
    }

}
