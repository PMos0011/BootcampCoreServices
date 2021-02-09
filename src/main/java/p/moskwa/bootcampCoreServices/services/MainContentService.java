package p.moskwa.bootcampCoreServices.services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class MainContentService implements MouseListener {
    private JLabel selectedLabel;

    public MainContentService(){
        selectedLabel = null;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        JLabel label = (JLabel) mouseEvent.getComponent();
        label.setBackground(Color.GRAY);

        if (selectedLabel != null)
            selectedLabel.setBackground(Color.WHITE);

        selectedLabel = label;
        getMainWindow().displaySongDetails(label.getName());
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != selectedLabel)
            mouseEvent.getComponent().setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != selectedLabel)
            mouseEvent.getComponent().setBackground(Color.WHITE);

    }
}
