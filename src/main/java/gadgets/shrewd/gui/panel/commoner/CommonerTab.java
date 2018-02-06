package gadgets.shrewd.gui.panel.commoner;

import gadgets.shrewd.core.util.Words;
import gadgets.shrewd.gui.panel.TabbedPanel;
import gadgets.shrewd.gui.panel.TextFieldInputGroup;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class CommonerTab extends TabbedPanel {

    private static final int DEFAULT_WIDTH = 200;

    public CommonerTab() {
        TextFieldInputGroup primary = new TextFieldInputGroup.Builder()
                .text("First set:")
                .columns(DEFAULT_WIDTH)
                .build();
        TextFieldInputGroup secondary = new TextFieldInputGroup.Builder()
                .text("Second set:")
                .columns(DEFAULT_WIDTH)
                .build();
        TextFieldInputGroup result = new TextFieldInputGroup.Builder()
                .text("Common:")
                .uneditable()
                .columns(DEFAULT_WIDTH)
                .build();

        Button button = new CommonerTab.Button(
                primary.getComponent(),
                secondary.getComponent(),
                result.getComponent());

        this.containers = Arrays.asList(primary, secondary, result, button);
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

}
