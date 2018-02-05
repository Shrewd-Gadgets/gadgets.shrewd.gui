package gadgets.shrewd.gui.panel;

import javax.swing.*;
import java.util.Optional;

public interface Tabable {

    String getTitle();

    ImageIcon getIcon();

    Optional<String> getTip();
}
