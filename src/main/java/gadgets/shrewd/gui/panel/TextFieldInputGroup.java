package gadgets.shrewd.gui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Implementation of the input group interface specifically for a Swing TextField
 * Component as a thin free form panel.
 */
public class TextFieldInputGroup extends JPanel implements InputGroupable<JTextField> {

    private JLabel label;
    private JTextField component;

    private TextFieldInputGroup(Builder builder) {
        this.label = new JLabel(builder.text);
        this.component = new JTextField(builder.field);
        this.component.setPreferredSize(new Dimension(builder.cols, 30));
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

    /**
     * Public builder class for constructing the label and text field pieces.
     */
    public static class Builder {
        private static final int MINIMUM_COLS = 120;

        private String text;
        private String field;
        private int cols;
        private boolean isEditable = true;

        /**
         * Uses the set values in the builder to construct a new instance of a input group.
         * @return New TextFieldInputGroup instance.
         */
        public TextFieldInputGroup build() { return new TextFieldInputGroup(this); }

        /**
         * The label text displayed to prompt a user for input.
         * The text is displayed "as is" so any punctuation like colons or hyphens must
         * be included as part of the text.
         * @param text Displayable text, e.g. "Name"
         * @return Builder instance.
         */
        public Builder text(String text) { this.text = text; return this; }

        /**
         * Optional starting text for the text field.
         * @param field Field text to be displayed.
         * @return Builder instance.
         */
        public Builder field(String field) { this.field = field; return this; }

        /**
         * Optional static column size for the text field.
         * Values less than the minimum will be ignored.
         * @param cols Field column, i.e. character, width.
         * @return Builder instance.
         * @see Builder#MINIMUM_COLS
         */
        public Builder columns(int cols) { this.cols = cols < MINIMUM_COLS ? MINIMUM_COLS : cols; return this; }

        /**
         * The text field will accept user input.
         * @return Builder instance.
         * @see Builder#uneditable()
         */
        public Builder editable() { this.isEditable = true; return this; }

        /**
         * The text field will <u>not</u> accept user input.
         * @return Builder instance.
         * @see Builder#editable()
         */
        public Builder uneditable() { this.isEditable = false; return this; }

    }
}
