package gadgets.shrewd.gui.panel;

import gadgets.shrewd.gui.panel.commoner.Commoner;
import gadgets.shrewd.gui.panel.scambler.Scrambler;

import javax.swing.*;
import java.awt.*;

public class HomePane extends JPanel {

    public HomePane() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        Scrambler scrambler = new Scrambler();
        tabbedPane.addTab(
                scrambler.getTitle(),
                scrambler.getIcon(),
                scrambler,
                scrambler.getTip().orElse(scrambler.getTitle()));

        Commoner commoner = new Commoner();
        commoner.load();
        tabbedPane.addTab(
                commoner.getTitle(),
                commoner.getIcon(),
                commoner,
                commoner.getTip().orElse(commoner.getTitle()));

        //Add the tabbed pane to this panel.
        this.add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }




}
