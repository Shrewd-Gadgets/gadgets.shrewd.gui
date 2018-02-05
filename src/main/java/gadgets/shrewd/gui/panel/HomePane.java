package gadgets.shrewd.gui.panel;

import gadgets.shrewd.gui.panel.scambler.Scrambler;

import javax.swing.*;
import java.awt.*;

public class HomePane extends JPanel {

    public HomePane() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        Scrambler scrambler = new Scrambler(this);
        tabbedPane.addTab(
                scrambler.getTitle(),
                scrambler.getIcon(),
                scrambler,
                scrambler.getTip().orElse(scrambler.getTitle()));

        //Add the tabbed pane to this panel.
        this.add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }




}
