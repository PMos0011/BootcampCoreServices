package p.moskwa.bootcampCoreServices.gui.services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

/**
 * Main content container services
 *
 * @since 1.0
 */
public class MainContentService implements MouseListener {
    private JLabel selectedLabel;

    public MainContentService() {
        selectedLabel = null;
    }

    /**
     * Displays song details cursor pointed song on mouse button clicked
     *
     * @param mouseEvent calling component event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        JLabel label = (JLabel) mouseEvent.getComponent();
        label.setBackground(Color.GRAY);

        if (selectedLabel != null)
            selectedLabel.setBackground(Color.WHITE);

        selectedLabel = label;
        getMainWindowInstance().displaySongDetails(label.getName());
    }

    /**
     * Not implemented
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    /**
     * Not implemented
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    /**
     * Marks cursor pointed song on mouse enter action
     *
     * @param mouseEvent calling component event
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != selectedLabel)
            mouseEvent.getComponent().setBackground(Color.LIGHT_GRAY);
    }

    /**
     * Clears mark cursor pointed song on mouse leave action
     *
     * @param mouseEvent calling component event
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != selectedLabel)
            mouseEvent.getComponent().setBackground(Color.WHITE);
    }
}
