package gadgets.shrewd.gui.panel;

import gadgets.shrewd.gui.panel.commoner.Commoner;
import gadgets.shrewd.gui.panel.scambler.Scrambler;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class HomePane extends JPanel {
    
    public HomePane() {
        super(new GridLayout(1, 2));
        JTabbedPane tabbedPane = new JTabbedPane();

        //Build a list of the tabs in the order to be displayed left-to-right.
        List<TabbedPanel> tabbedPanels = Arrays.asList(
                new Scrambler(),
                new Commoner());

        //Iterate over the tab panes, loading internal containers.
        tabbedPanels.forEach(p -> {
            //Load internal containers
            p.load();
            //Add the loaded tab to the pane
            tabbedPane.addTab(
                    p.getTitle(),
                    p.getIcon(),
                    p,
                    p.getTip().orElseGet(p::getTitle));
        });

        //Add the tabbed pane to this panel.
        this.add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }
}
