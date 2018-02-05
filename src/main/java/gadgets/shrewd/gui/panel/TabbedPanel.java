package gadgets.shrewd.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public abstract class TabbedPanel extends JPanel implements Tabable {

    protected Collection<Container> containers;

    public TabbedPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public Collection<Container> getContainers() {
        return Objects.isNull(this.containers) ?
                Collections.emptyList() :
                this.containers;
    }

    public void load() {
        Collection<Container> containers = this.getContainers();
        this.load(containers);
    }

    public void load(Collection<Container> containers) {
        if (Objects.isNull(containers) || containers.isEmpty())
            return;

        containers.forEach(this::add);
    }
}
