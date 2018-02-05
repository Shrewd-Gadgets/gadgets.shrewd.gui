package gadgets.shrewd.gui.panel.scambler;

import gadgets.shrewd.core.util.Words;
import gadgets.shrewd.gui.panel.FormInputGroupable;
import gadgets.shrewd.gui.panel.Tabable;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Scrambler extends JPanel implements Tabable {

    public Scrambler(Container parent) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        Sentence sentence = new Scrambler.Sentence();
        Reversed reversed = new Scrambler.Reversed();
        Container button = new Scrambler.Button(
                sentence.getComponent(),
                reversed.getComponent());

        this.add(sentence);
        this.add(reversed);
        this.add(button);
    }

    class Sentence extends JPanel implements FormInputGroupable<JTextField> {

        private JLabel label;
        private JTextField component;

        Sentence() {
            this.label = new JLabel("Sentence:");
            this.component = new JTextField("Here is a default sentence.");

            this.add(label);
            this.add(component);
        }

        @Override
        public JLabel getLabel() {
            return this.label;
        }

        @Override
        public JTextField getComponent() {
            return this.component;
        }
    }

    class Reversed extends JPanel implements FormInputGroupable<JTextField> {

        private JLabel label;
        private JTextField component;

        Reversed() {
            super(new GridBagLayout());
            this.label = new JLabel("Reversed:");

            this.component = new JTextField();
            this.component.setEditable(false);
            this.component.setAutoscrolls(true);

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            this.add(label);
            this.add(component, c);
        }

        @Override
        public JLabel getLabel() {
            return this.label;
        }

        @Override
        public JTextField getComponent() {
            return this.component;
        }
    }

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
    public ImageIcon getIcon() {
        return null;
    }

    @Override
    public Optional<String> getTip() {
        return Optional.of("Reverse your sentences to confuse your enemies!");
    }

}
