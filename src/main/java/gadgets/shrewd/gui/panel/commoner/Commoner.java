package gadgets.shrewd.gui.panel.commoner;

import gadgets.shrewd.core.util.Words;
import gadgets.shrewd.gui.panel.TabbedPanel;
import gadgets.shrewd.gui.panel.TextFieldInputGroup;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class Commoner extends TabbedPanel {

    public Commoner() {
        TextFieldInputGroup primary =
                new TextFieldInputGroup("First string:", null, true);
        TextFieldInputGroup secondary =
                new TextFieldInputGroup("Second string:", null, true);
        TextFieldInputGroup result =
                new TextFieldInputGroup("Common characters:", null, false);

        Button button = new Commoner.Button(
                primary.getComponent(),
                secondary.getComponent(),
                result.getComponent());

        this.containers = Arrays.asList(primary, secondary, result, button);
    }

    @Override
    public String getTitle() {
        return "Commoner";
    }

    @Override
    public ImageIcon getIcon() {
        return null;
    }

    @Override
    public Optional<String> getTip() {
        return Optional.of("Find common characters between strings.");
    }

    class Button extends JPanel {
        Button(JTextField primary, JTextField secondary, JTextField result) {
            JButton button = new JButton("INTERSECTION");

            button.addActionListener(e -> {
                String p = primary.getText();
                String s = secondary.getText();
                String intersection = Words.intersection(p, s);

                if (intersection.isEmpty()) {
                    result.setDisabledTextColor(Color.BLUE);
                    result.setEnabled(false);
                    result.setText("No characters in common!");
                } else {
                    result.setEnabled(true);
                    result.setText(intersection);
                }

                result.repaint();
            });

            this.add(button);
        }
    }
}
