package gadgets.shrewd.gui.panel;

import javax.swing.*;

public class TextFieldInputGroup extends JPanel implements FormInputGroupable<JTextField> {

    private JLabel label;
    private JTextField component;

    private TextFieldInputGroup(Builder builder) {
        this.label = new JLabel(builder.text);
        this.component = new JTextField(
                builder.field,
                builder.cols > 0 ? builder.cols : 20);
        this.component.setEditable(builder.isEditable);

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

    public static class Builder {
        private String text;
        private String field;
        private int cols;
        private boolean isEditable = true;

        public TextFieldInputGroup build() { return new TextFieldInputGroup(this); }

        public Builder text(String text) { this.text = text; return this; }
        public Builder field(String field) { this.field = field; return this; }
        public Builder columns(int cols) { this.cols = cols; return this; }
        public Builder editable() { this.isEditable = true; return this; }
        public Builder uneditable() { this.isEditable = false; return this; }

    }
}
