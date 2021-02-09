package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.services.SidebarServices;

import javax.swing.*;
import java.awt.*;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class SideBar {
    private final JPanel sideBar;
    private final SidebarServices sidebarServices;

    public SideBar(){
        sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(300,768));
        sideBar.setBackground(Color.GRAY);

        sidebarServices = new SidebarServices();
    }

    public JPanel getSideBarContent() {
        return sideBar;
    }

    public void displayErrorConfirmButton(){
        sideBar.removeAll();
        JButton confirmErrorButton = new JButton("przeczytałem błędy");
        confirmErrorButton.addActionListener(action->getMainWindow().displaySong());
        sideBar.add(confirmErrorButton);
    }
}