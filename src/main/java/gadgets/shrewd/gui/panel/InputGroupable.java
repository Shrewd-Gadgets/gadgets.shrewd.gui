package gadgets.shrewd.gui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Simple interface template for an input group having both a
 * Label and an input Component
 * @param <COMPONENT> AWT Component template
 */
public interface InputGroupable<COMPONENT extends Component> {

    /**
     * Returns the Swing Label object for this input group.
     * @return Swing Label object.
     */
    JLabel getLabel();

    /**
     * Returns the AWT Component object for this input group.
     * @return AWT Component object.
     */
    COMPONENT getComponent();
}
