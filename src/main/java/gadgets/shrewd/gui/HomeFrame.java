package gadgets.shrewd.gui;

import gadgets.shrewd.gui.panel.HomePane;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        this.init();
    }

    private void init() {
        this.setTitle("Shrewd Devices");
        this.setSize(300,200); // default size is 0,0
        this.setLocation(10,200); // default is 0,0 (top left corner)

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        HomePane pane = new HomePane();
        this.add(pane, BorderLayout.CENTER);
    }
}
