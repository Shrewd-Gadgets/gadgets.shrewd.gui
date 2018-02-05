package gadgets.shrewd.gui.panel;

import javax.swing.*;

public interface FormInputGroupable<COMPONENT> {

    JLabel getLabel();

    COMPONENT getComponent();
}
