package gadgets.shrewd.gui.panel;

import javax.swing.*;

public class TextFieldInputGroup extends JPanel implements FormInputGroupable<JTextField> {

    private JLabel label;
    private JTextField component;

    public TextFieldInputGroup(String text, String field, boolean isEditable) {
        this.label = new JLabel(text);
        this.component = new JTextField(field);
        this.component.setEditable(isEditable);

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
