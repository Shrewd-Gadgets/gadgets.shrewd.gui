package gadgets.shrewd.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        this.init();
    }

    private void init() {
        this.setTitle("Shrewd Devices");
        this.setSize(300,200); // default size is 0,0
        this.setLocation(10,200); // default is 0,0 (top left corner)

        Terminator terminator = new Terminator();
        this.addWindowListener(terminator);
    }



    /**
     * Simple termination class that closes the process when the window is closed.
     */
    public static class Terminator extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
    }
}
