package p.moskwa.bootcampCoreServices.gui;

import javax.swing.*;

/**
 * Clears GUI view
 *
 * @since 1.0
 */
public abstract class InterfaceClear {

    /**
     * Removes all components in given component and refreshes the view
     *
     * @param component {@link JComponent} to clear and refresh
     */
    protected void clearView(JComponent component) {
        component.removeAll();
        component.repaint();
    }
}