package gadgets.shrewd.gui.panel;

import javax.swing.*;
import java.util.Optional;

/**
 * Describes a Swing panel that is part of a tab group.
 */
public interface Tabable {

    /**
     * Title for the tab
     * @return Tab text.
     */
    String getTitle();

    /**
     * Tab icon, if available.
     * @return Icon instance, if available.
     */
    Optional<ImageIcon> getIcon();

    /**
     * A tab tooltip that is displayed on hover of the tab, if available.
     * @return Tab tooltip text.
     */
    Optional<String> getTip();
}
